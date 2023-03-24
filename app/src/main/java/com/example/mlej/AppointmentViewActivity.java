package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class AppointmentViewActivity extends AppCompatActivity {

    DatabaseHelper db;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_view);

        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

    }
}