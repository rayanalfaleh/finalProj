package com.codingelab.tutorial;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLitedb extends AppCompatActivity {

    DBHelper mydb;

    Button bttnshow1;
    Button bttnshowall;
    Button bttnadd;
    Button button_last;

    TextView textViewName,textViewPhone;


    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText  editText;
    Button Search1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlitedb);

        ArrayAdapter<String> arrayAdapter;

        mydb = new DBHelper(this);

        editTextName = (EditText)findViewById(R.id.editName);
        editTextPhone = (EditText)findViewById(R.id.editPhone);
        editTextEmail = (EditText)findViewById(R.id.editEmail);

        bttnadd = (Button) findViewById(R.id.bttnAdd);
        bttnshow1 = (Button) findViewById(R.id.bttnShow1);
        bttnshowall = (Button) findViewById(R.id.bttnShowAll);

        bttnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //remove the following toast...
                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                String getName = editTextName.getText().toString();
                String getPhone = editTextPhone.getText().toString();
                String getEmail = editTextEmail.getText().toString();

                if (mydb.insertContact(getName, getPhone, getEmail)) {
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + getName + ", " + getPhone + "," + getEmail, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }
        });

        bttnshow1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.getData(1); //specific record (id=1)

                if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        button_last = (Button) findViewById(R.id.button);
        button_last.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.getData2(); //specific record (id=1)

                if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail, Toast.LENGTH_LONG).show();
                    textViewName.setText(dName);
                    textViewPhone.setText(dPhone);

                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });

        bttnshowall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Result.class);
                startActivity(intent);
            }
        });
    }

    public void search1(View view) {
        Cursor getData=mydb.getData3("name");

        editText = (EditText) findViewById(R.id.editText);
        Search1=(Button)findViewById(R.id.btnSearch);
        if (getData.moveToNext()) {
            Log.v("georgeLog", "data found in DB...");
            String dname = getData.getString(getData.getColumnIndex("name"));

        }else
            Toast.makeText(getApplicationContext(),
                    "did not get any data...:-(", Toast.LENGTH_LONG).show();
        getData.close();

        Search1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Result.class);
                startActivity(intent);
            }
        });
    }
}
