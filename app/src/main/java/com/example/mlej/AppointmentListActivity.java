package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        // setTitle("Upcoming Appointments");

        initData(userId);

        System.out.println("AppointmentListActivity: "+userId);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (getIntent().getBooleanExtra("upcoming", false)) {
            setTitle("Upcoming Appointments");
            if (appList != null) {
                for (int i = 0; i < appList.size(); i++) {
                    try {
                        if (sdf.parse(appList.get(i).getAppointDateTime()).before(calendar.getTime())) {
                            appList.remove(i);
                            i--;
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else {
            setTitle("Service History");
            if (appList != null) {
                for (int i = 0; i < appList.size(); i++) {
                    try {
                        if (sdf.parse(appList.get(i).getAppointDateTime()).after(calendar.getTime())) {
                            appList.remove(i);
                            i--;
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        if(appList != null) {
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            appointmentAdapter = new AppointmentAdapter(this, appList);
            recyclerView.setAdapter(appointmentAdapter);
        }
    }

    public void initData(int userId){
        appList = new ArrayList<>();
        dbh = new DatabaseHelper(this);
        if (dbh.getUserTypeById(userId) == 0) {
            appList = dbh.viewAppointment(userId);
        }
        else {
            appList = dbh.viewCustomerAppointment(userId);
        }
    }
}