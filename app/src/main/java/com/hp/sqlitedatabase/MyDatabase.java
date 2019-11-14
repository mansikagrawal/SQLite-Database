package com.hp.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 02-07-2019.
 */

public class MyDatabase extends SQLiteOpenHelper {


    public  static  final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="mydb";
    public static final String TABLE_NAME="emp_record";
    public static final String EMP_ID="id";

    public static final String EMP_NAME="name";
    public static final String EMP_ADDRESS="address";



    public MyDatabase(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
     {

         //create table jadu(id int,name text,address text);
         String query="CREATE TABLE "+TABLE_NAME+"("+EMP_ID+" NUMBER PRIMARY KEY,"+EMP_NAME+" TEXT,"+EMP_ADDRESS+" TEXT);";
         db.execSQL(query);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  long insertRecord(Record record)  //inserting data in form of sending object to increase the speed of app

    {
  SQLiteDatabase db=getWritableDatabase(); //called when to write dasta in database
        ContentValues values=new ContentValues();// it's a class jo data ko column unki rowas and colum m store krta h
values.put(EMP_ID,record.getId());
        values.put(EMP_NAME,record.getName());
        values.put(EMP_ADDRESS,record.getAddress());
       long result= db.insert(TABLE_NAME,null,values);//null to not make any column blank
        //reault gives 1 if data is written else return 0
        return  result;


    }
    public Record getSingleRecord(int empid)
    {
        //cursor is container which holds large amount of data in rows and columns

        // if ypu want to get the single record from sqlite database so you hve to call third query method of sqlite database class

         SQLiteDatabase db=getReadableDatabase();
         Cursor cursor=db.query(TABLE_NAME,new String[]{EMP_ID,EMP_NAME,EMP_ADDRESS},EMP_ID +"=?",new String[]{String.valueOf(empid)},null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();//move cursor to first row
            return new Record(cursor.getInt(0), cursor.getString(1), cursor.getString(2));//0.1.2 are column indexes}
        }
        else
        {
            return  null;
        }




    }


    public List<Record> getAllRecord()
    {
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<Record> arrayList=new ArrayList<Record>();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=database.rawQuery(query,null);//exedutes query and returns data
        if(cursor.moveToFirst())
        {
            do {


                Record record=new Record();
                record.setId(cursor.getInt(0));
                record.setName(cursor.getString(1));
                record.setAddress(cursor.getString(2));

                arrayList.add(record);//add all record to arraylist

            }while(cursor.moveToNext());
        }
        return  arrayList;




    }

    public long updateRecord(Record record)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(EMP_NAME,record.getName());
        values.put(EMP_ADDRESS,record.getAddress());
      long result=  database.update(TABLE_NAME,values,EMP_ID+"=?",new String[]{String.valueOf(record.getId())});
       return  result;



    }

    public long deleteRecord(int empId)

    {
      SQLiteDatabase database=  getWritableDatabase();
        long result=database.delete(TABLE_NAME,EMP_ID+"=?",new String[]{String.valueOf(empId)});
        return result;}

    }


