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
        userId = preferences.getInt("USERID",0);

        initData(userId);

        System.out.println("AppointmentListActivity: "+userId);

        if(appList != null) {
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            appointmentAdapter = new AppointmentAdapter(this, appList);
            recyclerView.setAdapter(appointmentAdapter);
        }
    }

    //this is just some dummy appointments
    public void initData(int userId){
        appList = new ArrayList<>();
        dbh = new DatabaseHelper(this);
        appList = dbh.viewAppointment(userId);


    }
}