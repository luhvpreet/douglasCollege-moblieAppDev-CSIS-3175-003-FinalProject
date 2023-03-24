package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentDetails extends AppCompatActivity {

    DatabaseHelper db;
    int appointmentId =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        TextView txtAppointmentDetails = findViewById(R.id.txtAppointmentDetails);
        Button btnCancelAppointment = findViewById(R.id.btnCancelAppointment);

        Intent intent = getIntent();
        db = new DatabaseHelper(this);


        if (intent != null){
            appointmentId = intent.getIntExtra("appointmentId", 0);
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

        btnCancelAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.cancelAppointment(appointmentId);
                Toast.makeText(AppointmentDetails.this, "Appointment cancelled successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AppointmentDetails.this, AppointmentListActivity.class);
                startActivity(intent);
            }
        });
    }
}