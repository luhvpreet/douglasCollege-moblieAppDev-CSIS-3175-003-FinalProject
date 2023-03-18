package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CustomerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        TextView txtWelcome = findViewById(R.id.txtCHUserName);
        String username = getIntent().getStringExtra("username");

        txtWelcome.setText("Welcome " + username + "!");
    }
}