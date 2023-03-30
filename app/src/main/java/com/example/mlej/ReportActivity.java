package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportActivity extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences preferences;

    StringBuilder str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        setTitle("Service Report");

        WebView webView = findViewById(R.id.webView);
        webView.loadData(generateHTML(1), "text/html", "UTF-8");
        FloatingActionButton fButtonShare = findViewById(R.id.fButtonPrint);

        Intent intent = getIntent();
        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (intent != null) {
            int appointmentId = intent.getIntExtra("appointmentId", 0);

            // User and Customer ID may be used to give files unique identifiable names.
            //int userId = preferences.getInt("USERID", 0);
            //int customerId = db.getCustomerIdFromAppointment(appointmentId);

            Calendar appointmentTime = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                appointmentTime.setTime(sdf.parse(db.getAppointment(appointmentId).getAppointDateTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            AppointmentItemModel aim = db.getAppointment(appointmentId);
            str = new StringBuilder();
            str.append("Customer Name: " + aim.getCustomerName() + "\n\n");
            str.append("Appointment Time: " + aim.getAppointDateTime() + "\n\n");
            //str.append("Appointment with " + aim.getCustomerName() + "\n");
            //str.append("on " + aim.getAppointDateTime() +" :\n\n");

            //0 means drop off and 1 means pickup
            if (aim.getDropOffOrPickup() == 0) {
                str.append("Appointment Type: Drop-off");
            } else {
                str.append("Appointment Type: Pickup");
            }

            String[] services = db.getServicesFromAppointment(appointmentId);

            str.append("Services Provided: \n\n");
            for (int i = 0; i < services.length; i++) {
                str.append("\t- " + services[i] + "\n");
            }

            str.append("\n");

            // is there a method/ query that gets the total cost of a specific appointment?
            str.append("Total: $xxx.xx\n\n");

            // ** Replace String generateHTML method perhaps, and just use a textview to print the details
            //txtAppointmentDetails.setText(str);

        }

        fButtonShare.setOnClickListener(v -> {

            try {

                int appointmentId = intent.getIntExtra("appointmentId", 0);
                int userId = preferences.getInt("USERID", 0);
                int customerId = db.getCustomerIdFromAppointment(appointmentId);

                String border = "------------------------------------------------------------------------------------------";

                Calendar currentTime = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String generateTime = "Service Report Generated on: " + sdf.format(currentTime.getTime()) + "\n\n";

                OutputStreamWriter file = new OutputStreamWriter(openFileOutput("A" + appointmentId + "U" + userId + "C" + customerId + ".txt", MODE_APPEND));
                file.write(border + "\n\n");
                file.write(str + "\n");
                file.write(generateTime + "\n\n");
                file.write(border + "\n\n");

                //Can replace the below code if fragment notification feature is implemented
                Toast.makeText(ReportActivity.this, "Service Report has been generated successfully", Toast.LENGTH_LONG).show();

                file.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }


    private String generateHTML(int appointmentId) {
        String html = "" +
                "<html>" +
                    "<body>" +
                        "<h1>Service Report</h1>" +
                        "<p>Customer Name: " + "John Doe" + "</p>" +
                        "<p>Appointment time: " + "2020-12-12 12:00" + "</p>" +
                        "<p>Appointment type: " + "Pickup" + "</p>" +
                        "<p>Services: </p>" +
                        "<ul>" +
                            "<li>Service 1</li>" +
                            "<li>Service 2</li>" +
                            "<li>Service 3</li>" +
                        "</ul>" +
                        "<p>Total: $xxx.xx</p>" +
                    "</body>" +
                "</html>";
        return html;
    }
}