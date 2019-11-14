package com.hp.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    MyDatabase database;

EditText e1,e2,e3;
    Button btn,btn2,btn3,btn4,btn5;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new MyDatabase(this);
        btn5=(Button)findViewById(R.id.button5);

        e1= (EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText);
        e3=(EditText)findViewById(R.id.editText3);
        btn3=(Button)findViewById(R.id.button);
        btn=(Button)findViewById(R.id.button2);btn4=(Button)findViewById(R.id.button4);
        btn2=(Button)findViewById(R.id.button3);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(e1.getText().toString());
            long data=database.deleteRecord(id);
            if(data>0)
                Toast.makeText(MainActivity.this, "kam ho gaya...", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Kuch gadbad h...", Toast.LENGTH_SHORT).show();}
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                int id=Integer.parseInt(s1);
                String name=e2.getText().toString();
                String address=e3.getText().toString();
                Record record=new Record();
                record.setId(id);
                record.setName(name);
                record.setAddress(address);

                 long result=database.updateRecord(record);
                if(result>0)
                    Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                int id=Integer.parseInt(s1);
                String name=e2.getText().toString();
                String address=e3.getText().toString();
                Record record=new Record();
                record.setId(id);
            record.setName(name);
            record.setAddress(address);
             long result =database.insertRecord(record);
                if(result>0)
                    Toast.makeText(MainActivity.this, "record inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Query problems", Toast.LENGTH_SHORT).show();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int id=Integer.parseInt(e1.getText().toString());
                Record record =database.getSingleRecord(id);
                if(record!=null)
                    Toast.makeText(MainActivity.this, "name:" +record.getName() +" Address:" +record.getAddress(), Toast.LENGTH_SHORT).show();
                    else
                Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Record> list=database.getAllRecord();
                for(Record record:list)
                {
                    Toast.makeText(MainActivity.this, "name:" +record.getName() +" Address:" +record.getAddress(), Toast.LENGTH_SHORT).show();
                }
            }
        });










    }
}
