package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(LoginActivity.this);

        Button btnLSignup = findViewById(R.id.btnLSignup);
        Button btnLLogin = findViewById(R.id.btnLLogin);
        EditText txtLEmail = findViewById(R.id.etxtUsername);
        EditText txtLPassword = findViewById(R.id.etxtPassword);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

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
                // get the email and password from the text fields
                String email = txtLEmail.getText().toString();
                String password = txtLPassword.getText().toString();

                // check if the email and password are valid, -1 means no such user, 0 or up means userId
                int userId = db.verifyLogin(email, password);
                System.out.println("loginActivity: "+userId);

                if (userId != -1) {
                    editor.putInt("USERID",userId);
                    editor.commit();
                    txtLEmail.setText("");
                    txtLPassword.setText("");
                    if (db.getUserTypeById(userId) == 0) {
                        // if the user is a provider, go to the ProviderHome activity
                        Intent intent = new Intent(LoginActivity.this, ServiceProviderHomeActivity.class);
                        startActivity(intent);
                    } else {
                        // if the user is a customer, go to the CustomerHome activity
                        Intent intent = new Intent(LoginActivity.this, CustomerHomeActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}