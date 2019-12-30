package com.codingelab.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dbupdate extends AppCompatActivity {

    EditText name,phone,email;
    Button update;
    private Syn syn;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbupdate);

        this.syn=new Syn();


        update=(Button)findViewById(R.id.bttnInsert);
        //   delete=(Button)findViewById(R.id.bttnDelete);
        name=(EditText)findViewById(R.id.editTxtName);
        phone=(EditText)findViewById(R.id.editTxtPhone);
        email=(EditText)findViewById(R.id.editTxtEmail);
        // id=(EditText)findViewById(R.id.editTxtDelete);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
        id = intent.getStringExtra("id");
        final Intent updateIntent = new Intent(this, Listviewupdate.class);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=syn.doInBackground("update",name.getText().toString(),phone.getText().toString(),email.getText().toString(), id);
                Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
                startActivity(updateIntent);
            }
        });
    }
}
