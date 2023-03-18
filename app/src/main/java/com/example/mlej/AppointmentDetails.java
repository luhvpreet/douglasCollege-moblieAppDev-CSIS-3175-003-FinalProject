package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AppointmentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        TextView txtAppointmentDetails = findViewById(R.id.txtAppointmentDetails);

        Intent intent = getIntent();
        if (intent != null){
            int appointmentId = intent.getIntExtra("appointmentId", 0);

            txtAppointmentDetails.setText(String.valueOf(appointmentId));

        }

    }
}