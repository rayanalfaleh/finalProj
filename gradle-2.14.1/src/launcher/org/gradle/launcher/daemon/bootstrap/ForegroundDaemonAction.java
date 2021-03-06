/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.launcher.daemon.bootstrap;

import com.google.common.collect.ImmutableList;
import org.gradle.internal.classpath.DefaultClassPath;
import org.gradle.internal.logging.LoggingManagerInternal;
import org.gradle.internal.service.ServiceRegistry;
import org.gradle.launcher.daemon.configuration.DaemonServerConfiguration;
import org.gradle.launcher.daemon.registry.DaemonRegistry;
import org.gradle.launcher.daemon.server.AnyDaemonExpirationStrategy;
import org.gradle.launcher.daemon.server.Daemon;
import org.gradle.launcher.daemon.server.DaemonExpirationStrategy;
import org.gradle.launcher.daemon.server.DaemonIdleTimeoutExpirationStrategy;
import org.gradle.launcher.daemon.server.DaemonRegistryUnavailableExpirationStrategy;
import org.gradle.launcher.daemon.server.DaemonServices;

import java.util.concurrent.TimeUnit;

public class ForegroundDaemonAction implements Runnable {

    private final ServiceRegistry loggingRegistry;
    private final DaemonServerConfiguration configuration;

    public ForegroundDaemonAction(ServiceRegistry loggingRegistry, DaemonServerConfiguration configuration) {
        this.loggingRegistry = loggingRegistry;
        this.configuration = configuration;
    }

    public void run() {
        LoggingManagerInternal loggingManager = loggingRegistry.newInstance(LoggingManagerInternal.class);
        loggingManager.start();

        DaemonServices daemonServices = new DaemonServices(configuration, loggingRegistry, loggingManager, new DefaultClassPath());
        Daemon daemon = daemonServices.get(Daemon.class);

        daemon.start();

        try {
            daemonServices.get(DaemonRegistry.class).markIdle(daemon.getAddress());
            daemon.stopOnExpiration(initializeExpirationStrategy(configuration), configuration.getPeriodicCheckIntervalMs());
        } finally {
            daemon.stop();
        }
    }

    private DaemonExpirationStrategy initializeExpirationStrategy(final DaemonServerConfiguration config) {
        DaemonIdleTimeoutExpirationStrategy timeoutStrategy = new DaemonIdleTimeoutExpirationStrategy(config.getIdleTimeout(), TimeUnit.MILLISECONDS);
        DaemonRegistryUnavailableExpirationStrategy registryUnavailableStrategy = new DaemonRegistryUnavailableExpirationStrategy();
        return new AnyDaemonExpirationStrategy(ImmutableList.of(timeoutStrategy, registryUnavailableStrategy));
    }
}
