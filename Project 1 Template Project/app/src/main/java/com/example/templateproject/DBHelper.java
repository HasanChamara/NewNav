package com.example.templateproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="MyDB.db";



    public DBHelper(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(firstname TEXT, lastname TEXT, email TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");

    }

    public boolean insertData (String firstname, String lastname, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = MyDB.insert("users", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public boolean checkemail(EditText email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email = ?", new String[] {String.valueOf(email)});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkemailpassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email = ? and password = ?", new String[] {email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
