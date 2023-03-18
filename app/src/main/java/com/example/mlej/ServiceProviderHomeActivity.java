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
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_home);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getInt("USERID",0);

        //this is not yet done, for testing only
        Button btnSPHViewAppointment = findViewById(R.id.btnSPHViewAppointment);
        btnSPHViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is not yet done, for testing only
                Intent intent = new Intent(ServiceProviderHomeActivity.this, AppointmentListActivity.class);
                startActivity(intent);
            }
        });
        
        TextView txtWelcome = findViewById(R.id.txtSPHServiceProviderName);
        String username = getIntent().getStringExtra("username");

        txtWelcome.setText(username + "!");

        System.out.println("AppointmentListActivity: "+userId);
    }
}