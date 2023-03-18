package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    List<AppointmentItemModel> appList;
    AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        initData();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        appointmentAdapter = new AppointmentAdapter(this, appList);
        recyclerView.setAdapter(appointmentAdapter);
        appointmentAdapter.notifyDataSetChanged();
    }

    public void initData(){
        appList = new ArrayList<>();
        appList.add(new AppointmentItemModel(16547, "Lovepreet Smith", "March 22, 11:00 am"));
        appList.add(new AppointmentItemModel(24555, "Eric Davis", "March 22, 1:00 pm"));
        appList.add(new AppointmentItemModel(33434, "Mattrew Johnson", "March 22, 4:00 pm"));
        appList.add(new AppointmentItemModel(46895, "Jichi Jordan", "March 23, 11:00 am"));
        appList.add(new AppointmentItemModel(56544, "Mary Chan", "March 23, 2:30 pm"));
        appList.add(new AppointmentItemModel(666, "John Doe", "March 23, 4:30 pm"));
        appList.add(new AppointmentItemModel(7666, "David Maja", "March 24, 9:00 am"));
        appList.add(new AppointmentItemModel(833, "Sue Jarrie", "March 24, 11:30 am"));
        appList.add(new AppointmentItemModel(908, "Jen Xulia", "March 24, 5:00 pm"));
    }
}