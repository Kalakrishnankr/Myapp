package com.example.kalakrishnankr.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "mydb";
    private static  final String TABLE_NAME = "employee";
    private static final String  TABLE_FIELD_ID = "id";
    private static final String  TABLE_FIELD_NAME = "name";
    private static final String  TABLE_FIELD_LNAME = "lname";
    private static final String  TABLE_FIELD_DOB = "dob";
    private static final String  TABLE_FIELD_DOJ = "doj";
    private static final String  TABLE_FIELD_DEPARTMENT = "department";
    private static final String  TABLE_FIELD_POSITION = "position";
    private static final String  TABLE_FIELD_SALARY = "salary";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE employee " +"(id integer primary key,name text,lname text, dob text, doj text,department text,position text, salary text)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
    }

    public boolean insertTable(String name, String lname, String db, String dj, String dept, String pos, String salary) {

        try {
            SQLiteDatabase dbb = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",name);
            contentValues.put("lname",lname);
            contentValues.put("dob",db);
            contentValues.put("doj",dj);
            contentValues.put("department",dept);
            contentValues.put("position",pos);
            contentValues.put("salary",salary);
            long flag = dbb.insert("employee",null,contentValues);
            Log.e("",""+ flag);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return true;

    }

    public Cursor getData() {
        Cursor data = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            data = db.rawQuery("select * from employee",null);

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
