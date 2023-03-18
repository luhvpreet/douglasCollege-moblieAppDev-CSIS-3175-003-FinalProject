package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceProviderHomeActivity extends AppCompatActivity {

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
    }
}