package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLSignup = findViewById(R.id.btnLSignup);
        Button btnLLogin = findViewById(R.id.btnLLogin);
        EditText txtLEmail = findViewById(R.id.etxtUsername);
        EditText txtLPassword = findViewById(R.id.etxtPassword);

        //clicking on the signup button will go to the SignupAccountType activity
        btnLSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupAccountTypeActivity.class);
                startActivity(intent);
            }
        });

        btnLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(LoginActivity.this);
                // get the email and password from the text fields
                String email = txtLEmail.getText().toString();
                String password = txtLPassword.getText().toString();
                // check if the email and password are valid
                if (db.verifyLogin(email, password)) {
                    // for now, display a toast message
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // for now, display a toast message
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}