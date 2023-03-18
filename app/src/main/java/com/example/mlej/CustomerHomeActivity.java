package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerHomeActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        db = new DatabaseHelper(this);
        TextView txtWelcome = findViewById(R.id.txtCHUserName);
        String username = getIntent().getStringExtra("username");

        txtWelcome.setText("Welcome " + username + "!");

        // implement logout
        Button btnLogout = findViewById(R.id.btnCHLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}