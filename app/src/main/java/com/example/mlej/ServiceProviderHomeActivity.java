package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceProviderHomeActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseHelper db;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_home);

        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getInt("USERID",0);
        editor = preferences.edit();

        Button btnSPHViewAppointment = findViewById(R.id.btnSPHViewAppointment);
        Button btnSPHLogout = findViewById(R.id.btnSPHLogout);

        TextView txtWelcome = findViewById(R.id.txtSPHUserName);
        String username = db.getUserNameById(userId);
        String companyName = db.getCompanyName(userId);
        txtWelcome.setText("Welcome "+username + "!");

        Button btnEditProfile = findViewById(R.id.btnSPHEditProfile);
        Button btnViewProfile = findViewById(R.id.btnSPHViewProfile);
        Button btnServiceHistory = findViewById(R.id.btnSPHViewServiceHistory);
        Button btnSPHEditServices = findViewById(R.id.btnSPHEditServices);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderHomeActivity.this, ProfileEditActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderHomeActivity.this, ProfileViewActivity.class);
                intent.putExtra("id", userId);
                intent.putExtra("name", companyName);
                startActivity(intent);
            }
        });

        btnSPHViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderHomeActivity.this, AppointmentListActivity.class);
                intent.putExtra("upcoming", true);
                startActivity(intent);
            }
        });

        btnServiceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderHomeActivity.this, AppointmentListActivity.class);
                intent.putExtra("upcoming", false);
                startActivity(intent);
            }
        });

        btnSPHEditServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderHomeActivity.this, EditServicesActivity.class);
                startActivity(intent);
            }
        });

        btnSPHLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceProviderHomeActivity.this, WelcomeScreenActivity.class);
                intent.putExtra("isLogout", true);
                editor.remove("USERID");
                editor.commit();
                startActivity(intent);
            }
        });
    }
}