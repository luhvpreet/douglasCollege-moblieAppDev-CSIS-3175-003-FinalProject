package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AppointmentDetails extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        TextView txtAppointmentDetails = findViewById(R.id.txtAppointmentDetails);
        Intent intent = getIntent();
        db = new DatabaseHelper(this);

        if (intent != null){
            int appointmentId = intent.getIntExtra("appointmentId", 0);
            AppointmentItemModel aim = db.getAppointment(appointmentId);
            StringBuilder str = new StringBuilder();
            str.append("Appointment with " + aim.getCustomerName() + "\n");
            str.append("on " + aim.getAppointDateTime() +" :\n\n");

            String[] services = db.getServicesFromAppointment(appointmentId);

            str.append("Requiring the following services: \n\n");
            for(int i=0; i<services.length; i++)
            {
                str.append(services[i]+"\n");
            }

            str.append("\n");

            //0 means drop off and 1 means pickup
            if (aim.getDropOffOrPickup() == 0)
            {
                str.append("Customer will drop off the vehicle.");
            }
            else
            {
                str.append("Customer will pickup the vehicle.");
            }

            txtAppointmentDetails.setText(str);



        }

    }
}