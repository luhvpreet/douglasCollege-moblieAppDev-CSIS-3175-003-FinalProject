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
                    if (db.getUserType(email) == 0) {
                        // if the user is a provider, go to the ProviderHome activity
                        Intent intent = new Intent(LoginActivity.this, ServiceProviderHomeActivity.class);
                        intent.putExtra("username", db.getUserName(email));
                        startActivity(intent);
                    } else {
                        // if the user is a customer, go to the CustomerHome activity
                        Intent intent = new Intent(LoginActivity.this, CustomerHomeActivity.class);
                        intent.putExtra("username", db.getUserName(email));
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}