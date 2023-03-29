package com.example.mlej;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KMutableProperty1;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "database.db";
    final static int DATABASE_VERSION = 35;
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
    final static String T1COL9 = "CompanyName";
    final static String TABLE2_NAME = "Appointment_table";
    final static String T2COL1 = "AppointmentId";
    final static String T2COL2 = "CustomerId";
    final static String T2COL3 = "ProviderId";
    final static String T2COL4 = "VehicleId";
    final static String T2COL5 = "DateTime";
    //0 means drop off and 1 means pickup
    final static String T2COL6 = "DropOffOrPickUp";

    final static String TABLE3_NAME = "Services_Table";
    final static String T3COL1 = "ServicesID";
    final static String T3COL2 = "ServicesName";

    final static String TABLE4_NAME = "Provider_Services_Table";
    final static String T4COL1 = "ProviderId";
    final static String T4COL2 = "ServicesID";

    final static String TABLE5_NAME = "Reminders_table";
    final static String T5COL1 = "ReminderId";
    final static String T5COL2 = "SenderId";
    final static String T5COL3 = "RecipientId";
    final static String T5COL4 = "AppointmentId";

    final static String TABLE6_NAME = "Appointment_Services_Table";
    final static String T6COL1 = "AppointmentId";
    final static String T6COL2 = "ServicesId";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME +
                "( " + T1COL1 + " INTEGER PRIMARY KEY, " + T1COL2 + " INTEGER,"
                + T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT,"
                + T1COL6 + " TEXT," + T1COL7 + " TEXT," + T1COL8 + " TEXT,"
                + T1COL9 +  " TEXT)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TABLE2_NAME +
                "( " + T2COL1 + " INTEGER PRIMARY KEY, " + T2COL2 + " INTEGER,"
                + T2COL3 + " INTEGER," + T2COL4 + " INTEGER," + T2COL5 + " TEXT,"
                + T2COL6 + " INTEGER)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TABLE3_NAME +
                "( " + T3COL1 + " INTEGER PRIMARY KEY, " + T3COL2 + " TEXT)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TABLE4_NAME +
                "( " + T4COL1 + " INTEGER, " + T4COL2 + " INTEGER)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TABLE5_NAME +
                "( " + T5COL1 + " INTEGER PRIMARY KEY, " + T5COL2 + " INTEGER,"
                + T5COL3 + " INTEGER," + T5COL4 + " INTEGER)";
        sqLiteDatabase.execSQL(query);
                
        query = "CREATE TABLE " + TABLE6_NAME +
                "( " + T6COL1 + " INTEGER, " + T6COL2 + " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        onCreate(sqLiteDatabase);
    }

    // method to add user to database
    // not secure, not suitable for production
    public boolean addUser(int type, String name, String email, String password, String phone, String address, String postalCode, String companyName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,type);
        values.put(T1COL3,name);
        values.put(T1COL4,email);
        values.put(T1COL5,password);
        values.put(T1COL6,phone);
        values.put(T1COL7,address);
        values.put(T1COL8,postalCode);
        values.put(T1COL9,companyName);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }

    //method to add user to database, but this one returns the ID of the inserted row
    public long addUserLong(int type, String name, String email, String password, String phone, String address, String postalCode, String companyName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2,type);
        values.put(T1COL3,name);
        values.put(T1COL4,email);
        values.put(T1COL5,password);
        values.put(T1COL6,phone);
        values.put(T1COL7,address);
        values.put(T1COL8,postalCode);
        values.put(T1COL9,companyName);

        return sqLiteDatabase.insert(TABLE1_NAME,null,values);
    }


    // method to verify user login
    // not secure, not suitable for production
    public int verifyLogin(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT ID FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "' AND " + T1COL5 + " = '" + password + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        else
            return -1;
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

    public int getUserTypeById(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL2 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
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

    public String getUserNameById(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL3 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    public String getUserPhone(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL6 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    public String getUserAddress(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL7 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    public String getUserPostalCode(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL8 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    public String getUserEmail(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL4 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    // WIP: method to update user information
    public boolean updateUser(int userId, String name, String email, String phone, String address, String postalCode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL3, name);
        values.put(T1COL4, email);
        values.put(T1COL6, phone);
        values.put(T1COL7, address);
        values.put(T1COL8, postalCode);
        return sqLiteDatabase.update(TABLE1_NAME, values, T1COL1 + " = " + userId, null) > 0;
    }

    public boolean addAppointment(int Customer_Id, int Provider_Id, int Vehicle_Id, String DateTime, int DropOffOrPickUp){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2,Customer_Id);
        values.put(T2COL3,Provider_Id);
        values.put(T2COL4,Vehicle_Id);
        values.put(T2COL5,DateTime);
        values.put(T2COL6,DropOffOrPickUp); //0 means drop off and 1 means pickup

        long l = sqLiteDatabase.insert(TABLE2_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }

    public AppointmentItemModel getAppointment(int appointmentId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        AppointmentItemModel aim = new AppointmentItemModel();

        String query = "SELECT User_table.Name, DateTime, DropOffOrPickup from Appointment_table " +
                "inner join User_table " +
                "on Appointment_table.CustomerId = User_table.Id " +
                "WHERE " + T2COL1 + "=" + appointmentId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToNext();
            aim.setCustomerName(cursor.getString(0));
            aim.setAppointDateTime(cursor.getString(1));
            aim.setDropOffOrPickup(cursor.getInt(2));
            return aim;
        }
        else
            return null;
    }

    public List<AppointmentItemModel> viewAppointment(int UserId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT AppointmentId, Name, DateTime from Appointment_table " +
                "inner join User_table " +
                "on Appointment_table.CustomerId = User_table.Id " +
                "WHERE ProviderId=" + UserId +
                " ORDER BY AppointmentId";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        List<AppointmentItemModel> appList;
        if(cursor.getCount() > 0){
            appList = new ArrayList<>();
            while(cursor.moveToNext()) {
                appList.add(new AppointmentItemModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
            }
            return appList;
        }
        else
            return null;
    }

    public List<AppointmentItemModel> viewCustomerAppointment(int UserId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT AppointmentId, Name, DateTime from Appointment_table " +
                "inner join User_table " +
                "on Appointment_table.ProviderId = User_table.Id " +
                "WHERE CustomerId=" + UserId +
                " ORDER BY AppointmentId";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        List<AppointmentItemModel> appList;
        if(cursor.getCount() > 0){
            appList = new ArrayList<>();
            while(cursor.moveToNext()) {
                appList.add(new AppointmentItemModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
            }
            return appList;
        }
        else
            return null;
    }

    public boolean addServices(int ID, String servicesName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL1,ID);
        values.put(T3COL2,servicesName);

        long l = sqLiteDatabase.insert(TABLE3_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }


    public boolean addProviderServices(int ProviderID, int ServicesID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL1,ProviderID);
        values.put(T4COL2,ServicesID);

        long l = sqLiteDatabase.insert(TABLE4_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }

    // Providers adapter
    public List<ProviderItemModel> getProviders(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT Id, CompanyName, Email, Phone, Address, PostalCode from User_table " +
                "WHERE Type=0";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        List<ProviderItemModel> providerList;
        if (cursor.getCount() > 0) {
            providerList = new ArrayList<>();
            while (cursor.moveToNext()) {
                providerList.add(new ProviderItemModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            }
            return providerList;
        }
        else
            return null;
    }

    public String getCompanyName(int providerId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL9 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + providerId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    public String getCompanyNameByAppointmentId(int appointmentId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL9 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " +
                "(SELECT " + T2COL3 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId + ")";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else
            return null;
    }

    public boolean addReminder(int senderId, int receiverId, int appointmentId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL2, senderId);
        values.put(T5COL3, receiverId);
        values.put(T5COL4, appointmentId);
        long l = sqLiteDatabase.insert(TABLE5_NAME, null, values);
        if (l > 0)
            return true;
        else
            return false;
    }
    
   public boolean addAppointmentServices(int AppointmentID, int ServicesID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T6COL1,AppointmentID);
        values.put(T6COL2,ServicesID);

        long l = sqLiteDatabase.insert(TABLE6_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;
    }

    public List<ReminderItemModel> getReminders(int userId, Context context) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT ReminderId, SenderId, AppointmentId from Reminders_table where RecipientId = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        List<ReminderItemModel> reminderList;
        if (cursor.getCount() > 0) {
            reminderList = new ArrayList<>();
            while (cursor.moveToNext()) {
                reminderList.add(new ReminderItemModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        context));
            }
            return reminderList;
        } else
            return null;
    }

    public String getAppointmentDateTime(int appointmentId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T2COL5 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        } else
            return null;
    }

    public String[] getServicesFromAppointment (int appointmentID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String query = "SELECT DISTINCT " + T3COL2 +
                        " FROM " + TABLE3_NAME +
                        " INNER JOIN " + TABLE6_NAME +
                        " ON " + TABLE3_NAME + "." + T3COL1 + "=" + TABLE6_NAME +"." + T3COL1 + " WHERE " +
                        TABLE6_NAME + "." + T6COL1 + "=" + appointmentID;
        System.out.println(query);

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        String[] services;
        services = new String[cursor.getCount()];
        int i=0;

        while(cursor.moveToNext()) {
            services[i] = cursor.getString(0);
            i++;
        }

        return services;
    }

    public int getCustomerIdFromAppointment(int appointmentId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T2COL2 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        else
            return -1;
    }

    //this will delete all records in all of the tables
    public void deleteALLRecords(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("delete from "+ TABLE1_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE2_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE3_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE4_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE5_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE6_NAME);
    }
}
