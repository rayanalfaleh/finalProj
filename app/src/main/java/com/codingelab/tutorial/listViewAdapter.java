package com.codingelab.tutorial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingelab.tutorial.R;

import java.util.ArrayList;

public class listViewAdapter {
    public ArrayList<em> personlist;
    Activity activity;
    //SearchActivity s;

    public listViewAdapter(Activity activity, ArrayList<em> personlist) {
        super();
        this.activity = activity;
        this.personlist = personlist;
    }

    public int getCount() {
        return personlist.size();
    }


    public Object getItem(int position) {
        return personlist.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mSid;
        TextView mName;
        TextView mPhone;
        TextView mEmail;
        TextView mDepartment;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            holder = new ViewHolder();
            holder.mSid = (TextView) convertView.findViewById(R.id.idText);
            holder.mName = (TextView) convertView.findViewById(R.id.NameText);
            holder.mPhone = (TextView) convertView.findViewById(R.id.PhoneText);
            holder.mEmail = (TextView) convertView.findViewById(R.id.EmailText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        em item = personlist.get(position);
        holder.mSid.setText(item.getId());
        holder.mName.setText(item.getName().toString());
        holder.mPhone.setText(item.getPhone().toString());
        holder.mEmail.setText(item.getEmail().toString());
        return convertView;
    }
}
