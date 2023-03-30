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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        WebView webView = findViewById(R.id.webView);
        webView.loadData(generateHTML(1), "text/html", "UTF-8");
        FloatingActionButton fButtonShare = findViewById(R.id.fButtonPrint);
        fButtonShare.setOnClickListener(v -> {

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