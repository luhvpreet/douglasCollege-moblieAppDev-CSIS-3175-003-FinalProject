package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ServiceProviderHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_home);

        TextView txtWelcome = findViewById(R.id.txtSPHServiceProviderName);
        String username = getIntent().getStringExtra("username");

        txtWelcome.setText(username + "!");
    }
}