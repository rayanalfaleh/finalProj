package com.codingelab.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class phpdbActivity extends AppCompatActivity {

    EditText name,phone,email;
    Button insert;
    // EditText id;
    //   Button delete;
    private Syn syn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phpdb);

        this.syn=new Syn();

        insert=(Button)findViewById(R.id.bttnInsert);
        //   delete=(Button)findViewById(R.id.bttnDelete);
        name=(EditText)findViewById(R.id.editTxtName);
        phone=(EditText)findViewById(R.id.editTxtPhone);
        email=(EditText)findViewById(R.id.editTxtEmail);
        // id=(EditText)findViewById(R.id.editTxtDelete);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=syn.doInBackground("insert",name.getText().toString(),phone.getText().toString(),email.getText().toString());
                Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
            }
        });
        //
        // delete.setOnClickListener(new View.OnClickListener() {
        // @Override
        //   public void onClick(View view) {
        //String msg=syn.doInBackground("delete",id.getText().toString());
        //Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
    }
}
