package com.example.mlej;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "database.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "User_table";
    final static String T1COL1 = "Id";
    // user type, 0 for service provider, 1 for customer
    final static String T1COL2 = "Type";
    final static String T1COL3 = "Name";
    final static String T1COL4 = "Email";
    final static String T1COL5 = "Password";
    final static String T1COL6 = "Phone";
    final static String T1COL7 = "Address";
    final static String T1COL8 = "PostalCode";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME +
                "( " + T1COL1 + " INTEGER PRIMARY KEY, " + T1COL2 + " INTEGER,"
                + T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT,"
                + T1COL6 + " TEXT," + T1COL7 + " TEXT," + T1COL8 + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    // method to add user to database
    // not secure, not suitable for production
    public boolean addUser(int type, String name, String email, String password, String phone, String address, String postalCode){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,type);
        values.put(T1COL3,name);
        values.put(T1COL4,email);
        values.put(T1COL5,password);
        values.put(T1COL6,phone);
        values.put(T1COL7,address);
        values.put(T1COL8,postalCode);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }

    // method to verify user login
    // not secure, not suitable for production
    public boolean verifyLogin(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "' AND " + T1COL5 + " = '" + password + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public int getUserType(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL2 + " FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        else
            return -1;
    }

    public String getUserName(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL3 + " FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    // WIP: method to get user information
    // WIP: method to update user information
}
