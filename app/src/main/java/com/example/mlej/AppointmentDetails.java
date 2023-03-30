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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        Button btnCancelAppointment = findViewById(R.id.btnCancelAppointment);
        Button btnEditAppointment = findViewById(R.id.btnEditAppointment);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (intent != null){
            int appointmentId = intent.getIntExtra("appointmentId", 0);
            int userId = preferences.getInt("USERID", 0);
            int customerId = db.getCustomerIdFromAppointment(appointmentId);

            Calendar currentTime = Calendar.getInstance();
            Calendar appointmentTime = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                appointmentTime.setTime(sdf.parse(db.getAppointment(appointmentId).getAppointDateTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if(appointmentTime.before(currentTime)) {
                btnRemindCustomer.setVisibility(View.INVISIBLE);
                btnCancelAppointment.setVisibility(View.INVISIBLE);
                btnEditAppointment.setVisibility(View.INVISIBLE);
            }

            if (userId == customerId) {
                btnRemindCustomer.setVisibility(View.INVISIBLE);
                btnEditCustomerProfile.setVisibility(View.INVISIBLE);
                btnEditAppointment.setVisibility(View.INVISIBLE);
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
                        Intent intent = new Intent(AppointmentDetails.this, ServiceProviderHomeActivity.class);
                        startActivity(intent);
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
                    intent.putExtra("mainUserId", userId);
                    startActivity(intent);
                }
            });

            btnCancelAppointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db.deleteAppointment(appointmentId)) {
                        Toast.makeText(AppointmentDetails.this, "Appointment cancelled!", Toast.LENGTH_SHORT).show();
                        finish();
                        if (db.getUserTypeById(userId) == 0) {
                            // if the user is a provider, go to the ProviderHome activity
                            Intent intent = new Intent(AppointmentDetails.this, ServiceProviderHomeActivity.class);
                            startActivity(intent);
                        } else {
                            // if the user is a customer, go to the CustomerHome activity
                            Intent intent = new Intent(AppointmentDetails.this, CustomerHomeActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(AppointmentDetails.this, "Appointment cancellation failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnEditAppointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AppointmentDetails.this, AppointmentEditActivity.class);
                    intent.putExtra("userId", customerId);
                    intent.putExtra("mainUserId", userId);
                    intent.putExtra("appointmentId", appointmentId);
                    startActivity(intent);
                }
            });

        }

    }
}