package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceProviderHomeActivity extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_home);

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

        db = new DatabaseHelper(this);
        
        TextView txtWelcome = findViewById(R.id.txtSPHServiceProviderName);
        String username = getIntent().getStringExtra("username");

        txtWelcome.setText(username + "!");

        // implement logout
        Button btnLogout = findViewById(R.id.btnSPHLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}