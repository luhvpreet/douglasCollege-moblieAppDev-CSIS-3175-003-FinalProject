package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListActivity extends AppCompatActivity{

    DatabaseHelper dbh;
    RecyclerView recyclerView;
    List<AppointmentItemModel> appList;
    AppointmentAdapter appointmentAdapter;

    SharedPreferences preferences;
    int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getInt("userId",0);

        initData(userId);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        appointmentAdapter = new AppointmentAdapter(this, appList);
        recyclerView.setAdapter(appointmentAdapter);
        //appointmentAdapter.notifyDataSetChanged();
    }

    //this is just some dummy appointments
    public void initData(int userId){
        appList = new ArrayList<>();

        dbh = new DatabaseHelper(this);
        Cursor cursor = dbh.viewAppointment(userId);

        //AppointmentItemModel(int appointmentId, String customerName, String appointDateTime)
        //String query = "SELECT AppointmentId, Name, DateTime from Appointment_table " +
//                "inner join User_table " +
//                "on Appointment_table.CustomerId = User_table.Id " +
//                "WHERE ProviderId=" + UserId +
//                " ORDER BY AppointmentId";
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                appList.add(new AppointmentItemModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            }
        }

        /*
        appList.add(new AppointmentItemModel(16547, "Lovepreet Smith", "March 22, 11:00 am"));
        appList.add(new AppointmentItemModel(24555, "Eric Davis", "March 22, 1:00 pm"));
        appList.add(new AppointmentItemModel(33434, "Matthew Johnson", "March 22, 4:00 pm"));
        appList.add(new AppointmentItemModel(46895, "Jichi Jordan", "March 23, 11:00 am"));
        appList.add(new AppointmentItemModel(56544, "Mary Chan", "March 23, 2:30 pm"));
        appList.add(new AppointmentItemModel(666, "John Doe", "March 23, 4:30 pm"));
        appList.add(new AppointmentItemModel(7666, "David Maja", "March 24, 9:00 am"));
        appList.add(new AppointmentItemModel(833, "Sue Jarrie", "March 24, 11:30 am"));
        appList.add(new AppointmentItemModel(908, "Jen Xulia", "March 24, 5:00 pm"));
        */
    }
}