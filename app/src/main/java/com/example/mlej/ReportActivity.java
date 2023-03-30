package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class ReportActivity extends AppCompatActivity {
    DatabaseHelper db;
    AppointmentItemModel appointment;
    ServicesItemModel[] services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        int appointmentId = getIntent().getIntExtra("appointmentId", 0);

        WebView webView = findViewById(R.id.webView);
        webView.loadData(generateHTML(appointmentId), "text/html", "UTF-8");
        FloatingActionButton fButtonShare = findViewById(R.id.fButtonPrint);
        fButtonShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Service Report");
            intent.putExtra(Intent.EXTRA_TEXT, generateHTML(appointmentId));
            startActivity(Intent.createChooser(intent, "Share with"));
        });
    }

    private String generateHTML(int appointmentId) {
        db = new DatabaseHelper(this);
        appointment = db.getAppointment(appointmentId);
        String customerName = appointment.getCustomerName();
        String providerName = db.getProviderNameByAppointmentId(appointmentId);
        services = db.getServicesItemModelFromAppointment(appointmentId);
        String appointmentType = appointment.getDropOffOrPickup() == 0 ? "Drop Off" : "Pick Up";
        Double total = 0.00;

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("<title>Service Report</title>");
        html.append("<style>");
        html.append("table, th, td {");
        html.append("border: 1px solid black;");
        html.append("border-collapse: collapse;");
        html.append("}");
        html.append("th, td {");
        html.append("padding: 5px;");
        html.append("text-align: left;");
        html.append("}");
        html.append(".price {");
        html.append("text-align: right;");
        html.append("}");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<h1>Service Report</h1>");
        html.append("<h2>" + providerName + "</h2>");
        html.append("<p>Customer Name: " + customerName + "</p>");
        html.append("<p>Appointment time: " + appointment.getAppointDateTime() + "</p>");
        html.append("<p>Appointment type: " + appointmentType + "</p>");
        html.append("<table>");
        html.append("<tr>");
        html.append("<th>Service</th>");
        html.append("<th>Price</th>");
        html.append("</tr>");
        for (ServicesItemModel service : services) {
            html.append("<tr>");
            html.append("<td>" + service.getServicesName() + "</td>");
            html.append("<td class=\"price\">$" + service.getPrice() + "</td>");
            total += service.getPrice();
            html.append("</tr>");
        }
        html.append("</table>");
        html.append("<p>Total: $" + total + "</p>");
        html.append("</body>");
        html.append("</html>");

        String output = html.toString();
        return output;
    }
}