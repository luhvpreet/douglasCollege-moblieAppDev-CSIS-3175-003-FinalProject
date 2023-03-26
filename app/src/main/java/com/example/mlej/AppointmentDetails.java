package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentDetails extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        TextView txtAppointmentDetails = findViewById(R.id.txtAppointmentDetails);
        Intent intent = getIntent();
        db = new DatabaseHelper(this);
        Button btnRemindCustomer = findViewById(R.id.btnRemindCustomer);
        Button btnEditCustomerProfile = findViewById(R.id.btnEditCustomerProfile);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (intent != null){
            int appointmentId = intent.getIntExtra("appointmentId", 0);
            int userId = preferences.getInt("USERID", 0);
            int customerId = db.getCustomerIdFromAppointment(appointmentId);

            if (userId == customerId) {
                btnRemindCustomer.setVisibility(View.INVISIBLE);
                btnEditCustomerProfile.setVisibility(View.INVISIBLE);
            }

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

            btnRemindCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db.addReminder(userId, customerId, appointmentId)) {
                        Toast.makeText(AppointmentDetails.this, "Reminder sent!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AppointmentDetails.this, "Reminder failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnEditCustomerProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AppointmentDetails.this, ProfileEditActivity.class);
                    intent.putExtra("userId", customerId);
                    startActivity(intent);
                }
            });

        }

    }
}