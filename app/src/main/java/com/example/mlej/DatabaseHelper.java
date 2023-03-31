package com.example.mlej;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "database.db";
    final static int DATABASE_VERSION = 36;
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
    final static String T2COL5 = "DateTime";
    //0 means drop off and 1 means pickup
    final static String T2COL6 = "DropOffOrPickUp";

    final static String TABLE3_NAME = "Services_Table";
    final static String T3COL1 = "ServicesID";
    final static String T3COL2 = "ServicesName";
    final static String T3COL3 = "Price";

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
                + T2COL3 + " INTEGER," + T2COL5 + " TEXT,"
                + T2COL6 + " INTEGER)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + TABLE3_NAME +
                "( " + T3COL1 + " INTEGER PRIMARY KEY, " + T3COL2 + " TEXT," + T3COL3 + " REAL)";
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
        sqLiteDatabase.close();
        if(l>0) return true;
        else return false;
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

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        sqLiteDatabase.close();
        return l;
    }


    // method to verify user login
    // not secure, not suitable for production
    public int verifyLogin(String email, String password) {
        int userId;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT ID FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "' AND " + T1COL5 + " = '" + password + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            userId = cursor.getInt(0);
        }
        else userId=-1;

        cursor.close();
        sqLiteDatabase.close();
        return userId;
    }

    public int getUserType(String email){
        int userType;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL2 + " FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userType = cursor.getInt(0);
        }
        else userType = -1;

        cursor.close();
        sqLiteDatabase.close();
        return userType;
    }

    public int getUserTypeById(int userId){
        int userType;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL2 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userType = cursor.getInt(0);
        }
        else userType = -1;

        cursor.close();
        sqLiteDatabase.close();
        return userType;
    }

    public String getUserName(String email){
        String userName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL3 + " FROM " + TABLE1_NAME + " WHERE " + T1COL4 + " = '" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userName = cursor.getString(0);
        }
        else userName = null;

        cursor.close();
        sqLiteDatabase.close();
        return userName;
    }

    public String getUserNameById(int userId){
        String userName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL3 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userName = cursor.getString(0);
        }
        else userName = null;

        cursor.close();
        sqLiteDatabase.close();
        return userName;
    }

    public String getUserPhone(int userId){
        String userPhone;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL6 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userPhone = cursor.getString(0);
        }
        else userPhone = null;

        cursor.close();
        sqLiteDatabase.close();
        return userPhone;
    }

    public String getUserAddress(int userId){
        String userAddress;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL7 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userAddress = cursor.getString(0);
        }
        else userAddress = null;

        cursor.close();
        sqLiteDatabase.close();
        return userAddress;
    }

    public String getUserPostalCode(int userId){
        String userPostalCode;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL8 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userPostalCode = cursor.getString(0);
        }
        else userPostalCode = null;

        cursor.close();
        sqLiteDatabase.close();
        return userPostalCode;
    }

    public String getUserEmail(int userId){
        String userEmail;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL4 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userEmail = cursor.getString(0);
        }
        else userEmail = null;

        cursor.close();
        sqLiteDatabase.close();
        return userEmail;
    }

    public boolean updateUser(int userId, String name, String email, String phone, String address, String postalCode) {
        boolean updated;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL3, name);
        values.put(T1COL4, email);
        values.put(T1COL6, phone);
        values.put(T1COL7, address);
        values.put(T1COL8, postalCode);
        updated = sqLiteDatabase.update(TABLE1_NAME, values, T1COL1 + " = " + userId, null) > 0;
        sqLiteDatabase.close();
        return updated;
    }

//    public long addAppointment(int Customer_Id, int Provider_Id, int Vehicle_Id, String DateTime, int DropOffOrPickUp){
    public long addAppointment(int Customer_Id, int Provider_Id, String DateTime, int DropOffOrPickUp){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL2,Customer_Id);
        values.put(T2COL3,Provider_Id);
        values.put(T2COL5,DateTime);
        values.put(T2COL6,DropOffOrPickUp); //0 means drop off and 1 means pickup

        long l = sqLiteDatabase.insert(TABLE2_NAME,null,values);
        sqLiteDatabase.close();
        if(l>0) return l;
        else return -1;
    }

    public boolean deleteAppointment(int appointmentId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        boolean deleted1 = sqLiteDatabase.delete(TABLE6_NAME, T2COL1 + " = " + appointmentId, null) > 0;
        boolean deleted2 = sqLiteDatabase.delete(TABLE2_NAME, T2COL1 + " = " + appointmentId, null) > 0;
        sqLiteDatabase.close();
        return (deleted1 && deleted2);
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
        }
        else aim = null;

        cursor.close();
        sqLiteDatabase.close();
        return aim;
    }

    public List<AppointmentItemModel> viewAppointment(int UserId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT AppointmentId, Name, DateTime from Appointment_table " +
                "inner join User_table " +
                "on Appointment_table.CustomerId = User_table.Id " +
                "WHERE ProviderId=" + UserId +
                " ORDER BY DateTime";
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
        }
        else appList = null;

        cursor.close();
        sqLiteDatabase.close();
        return appList;
    }

    public boolean addServices(int ID, String servicesName, double price){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL1,ID);
        values.put(T3COL2,servicesName);
        values.put(T3COL3,price);

        long l = sqLiteDatabase.insert(TABLE3_NAME,null,values);
        sqLiteDatabase.close();
        if(l>0) return true;
        else return false;
    }

    public List<ServicesItemModel> getServicesFromProviderId(int providerId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT pst.servicesId, servicesName, price FROM Provider_Services_Table pst, Services_Table st " +
                "WHERE pst.servicesId=st.servicesId AND pst.providerId=" + providerId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        List<ServicesItemModel> servicesList;
        if (cursor.getCount() > 0) {
            servicesList = new ArrayList<>();
            while (cursor.moveToNext()) {
                servicesList.add(new ServicesItemModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2)));
            }
        }
        else servicesList = null;

        cursor.close();
        sqLiteDatabase.close();
        return servicesList;
    }

    public String getProviderNameByAppointmentId(int appointmentId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String providerName;
        String query = "SELECT CompanyName FROM User_table " +
                "WHERE Id = (SELECT ProviderId FROM Appointment_table WHERE AppointmentId=" + appointmentId + ")";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToNext();
            providerName = cursor.getString(0);
        }
        else providerName = null;

        cursor.close();
        sqLiteDatabase.close();
        return providerName;
    }

    public boolean addProviderServices(int ProviderID, int ServicesID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL1,ProviderID);
        values.put(T4COL2,ServicesID);

        long l = sqLiteDatabase.insert(TABLE4_NAME,null,values);
        sqLiteDatabase.close();
        if(l>0) return true;
        else return false;
    }

    public void removeProviderServices(int userId, int ServicesID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL("delete from " + TABLE4_NAME + " WHERE " +
                T4COL1 + "=" + userId + " AND " + T4COL2 + " = " + ServicesID);

        sqLiteDatabase.close();
    }

    public String getServicesName(int servicesId) {
        String servicesName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T3COL2 + " FROM " + TABLE3_NAME + " WHERE " + T3COL1 + " = " + servicesId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            servicesName = cursor.getString(0);
        }
        else servicesName = null;

        cursor.close();
        sqLiteDatabase.close();
        return servicesName;
    }

    public boolean hasServices(int userId, int serviceId) {
        //db.hasServices(userId, 1)
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean hasService;
        String query = "SELECT DISTINCT " + T4COL2 +
                " FROM " + TABLE4_NAME + " WHERE " +
                TABLE4_NAME + "." + T4COL1 + "=" + userId + " AND " + TABLE4_NAME +"."+T4COL2+ "=" + serviceId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0) hasService = true;
        else hasService = false;

        cursor.close();
        sqLiteDatabase.close();
        return hasService;
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
        }
        else providerList = null;

        cursor.close();
        sqLiteDatabase.close();
        return providerList;
    }

    public String getCompanyName(int providerId){
        String companyName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL9 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " + providerId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            companyName = cursor.getString(0);
        }
        else companyName = null;

        cursor.close();
        sqLiteDatabase.close();
        return companyName;
    }

    public String getCompanyNameByAppointmentId(int appointmentId){
        String companyName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T1COL9 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = " +
                "(SELECT " + T2COL3 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId + ")";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            companyName = cursor.getString(0);
        }
        else companyName = null;

        cursor.close();
        sqLiteDatabase.close();
        return companyName;
    }

    public boolean addReminder(int senderId, int receiverId, int appointmentId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL2, senderId);
        values.put(T5COL3, receiverId);
        values.put(T5COL4, appointmentId);
        long l = sqLiteDatabase.insert(TABLE5_NAME, null, values);
        sqLiteDatabase.close();
        if (l > 0) return true;
        else return false;
    }
    
   public boolean addAppointmentServices(int AppointmentID, int ServicesID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T6COL1,AppointmentID);
        values.put(T6COL2,ServicesID);

        long l = sqLiteDatabase.insert(TABLE6_NAME,null,values);
        sqLiteDatabase.close();
        if(l>0) return true;
        else return false;
    }

    public boolean removeAllServicesFromAppointment(int appointmentId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + TABLE6_NAME + " WHERE " + T6COL1 + "=" + appointmentId);
        sqLiteDatabase.close();
        return true;
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
        }
        else reminderList = null;

        cursor.close();
        sqLiteDatabase.close();
        return reminderList;
    }

    public boolean hasReminders(int userId) {
        int hasReminderInt;
        boolean hasReminder;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT count(*) from Reminders_table where RecipientId = " + userId;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            hasReminderInt = cursor.getInt(0);
            if (hasReminderInt>0) hasReminder = true;
            else hasReminder = false;
        }
        else hasReminder = false;

        cursor.close();
        sqLiteDatabase.close();
        return hasReminder;
    }

    public void removeReminder(int reminderId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("delete from " + TABLE5_NAME + " WHERE " +
                T5COL1 + "=" + reminderId);
        sqLiteDatabase.close();
    }

    public void removeReminderByAppointmentId(int appointmentId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("delete from " + TABLE5_NAME + " WHERE " +
                T5COL4 + "=" + appointmentId);
        sqLiteDatabase.close();
    }

    public String getAppointmentDateTime(int appointmentId) {
        String appointmentDateTime;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T2COL5 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            appointmentDateTime = cursor.getString(0);
        }
        else appointmentDateTime = null;

        cursor.close();
        sqLiteDatabase.close();
        return appointmentDateTime;
    }
    public String getAppointmentType(int appointmentId) {
        String appointmentType;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T2COL6 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            appointmentType = cursor.getString(0);
        }
        else appointmentType = null;

        cursor.close();
        sqLiteDatabase.close();
        return appointmentType;
    }

    public boolean updateAppointment(int appointmentId, String appointmentDateTime, String appointmentType) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL5, appointmentDateTime);
        values.put(T2COL6, appointmentType);
        long l = sqLiteDatabase.update(TABLE2_NAME, values, T2COL1 + "=" + appointmentId, null);
        sqLiteDatabase.close();
        if (l > 0) return true;
        else return false;
    }
    public boolean appointmentHasServices(int aId, int serviceId) {
        boolean hasServices;
        //db.hasServices(userId, 1)
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT DISTINCT " + T6COL2 +
                " FROM " + TABLE6_NAME + " WHERE " +
                T6COL1 + "=" + aId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0) hasServices = true;
        else hasServices = false;

        cursor.close();
        sqLiteDatabase.close();
        return hasServices;
    }

    public String[] getServicesFromAppointment (int appointmentID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String query = "SELECT DISTINCT " + T3COL2 +
                        " FROM " + TABLE3_NAME +
                        " INNER JOIN " + TABLE6_NAME +
                        " ON " + TABLE3_NAME + "." + T3COL1 + "=" + TABLE6_NAME +"." + T3COL1 + " WHERE " +
                        TABLE6_NAME + "." + T6COL1 + "=" + appointmentID;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        String[] services;
        services = new String[cursor.getCount()];
        int i=0;

        while(cursor.moveToNext()) {
            services[i] = cursor.getString(0);
            i++;
        }
        cursor.close();
        sqLiteDatabase.close();
        return services;
    }

    public List<Integer> getServicesIdFromAppointment (int appointmentID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String query = "SELECT DISTINCT Services_Table." + T3COL1 +
                " FROM " + TABLE3_NAME +
                " INNER JOIN " + TABLE6_NAME +
                " ON " + TABLE3_NAME + "." + T3COL1 + "=" + TABLE6_NAME +"." + T3COL1 + " WHERE " +
                TABLE6_NAME + "." + T6COL1 + "=" + appointmentID;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        List<Integer> services;
        services = new ArrayList<>();
        int i=0;

        while(cursor.moveToNext()) {
            services.add(cursor.getInt(0));
            i++;
        }
        cursor.close();
        sqLiteDatabase.close();
        return services;
    }

    public ServicesItemModel[] getServicesItemModelFromAppointment (int appointmentID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String query = "SELECT DISTINCT " + "st." + T3COL1 + "," + "st." + T3COL2 + "," + "st." + T3COL3 +
                " FROM " + TABLE3_NAME + " st" +
                " INNER JOIN " + TABLE6_NAME +
                " ON " + "st" + "." + T3COL1 + "=" + TABLE6_NAME +"." + T3COL1 + " WHERE " +
                TABLE6_NAME + "." + T6COL1 + "=" + appointmentID;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        ServicesItemModel[] services;
        services = new ServicesItemModel[cursor.getCount()];
        int i=0;

        while(cursor.moveToNext()) {
            services[i] = new ServicesItemModel(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2));
            i++;
        }
        cursor.close();
        sqLiteDatabase.close();
        return services;
    }

    public int getCustomerIdFromAppointment(int appointmentId){
        int customerId;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + T2COL2 + " FROM " + TABLE2_NAME + " WHERE " + T2COL1 + " = " + appointmentId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            customerId = cursor.getInt(0);
        }
        else customerId = -1;

        cursor.close();
        sqLiteDatabase.close();
        return customerId;
    }

    //this will delete all records in all of the tables
    public void deleteALLRecords(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ TABLE1_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE2_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE3_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE4_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE5_NAME);
        sqLiteDatabase.execSQL("delete from "+ TABLE6_NAME);
        sqLiteDatabase.close();
    }

    public List<AppointmentItemModel> viewCustomerAppointment(int userId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT AppointmentId, Name, DateTime from Appointment_table " +
                    "inner join User_table " +
                    "on Appointment_table.ProviderId = User_table.Id " +
                    "WHERE CustomerId=" + userId  +
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
        }
        else appList = null;

        sqLiteDatabase.close();
        cursor.close();
        return appList;
    }

    public int getUserCount(){
        int userCount;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE1_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            userCount = cursor.getInt(0);
        }
        else userCount = 0;

        sqLiteDatabase.close();
        cursor.close();
        return userCount;
    }
}
