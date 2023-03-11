package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Button btnLSLogin = findViewById(R.id.btnLSLogin);
        Button btnLSSignup = findViewById(R.id.btnLSSignup);

        //clicking on the Login button will go to the Login activity
        btnLSLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //clicking on the signup button will go to the SignupAccountType activity
        btnLSSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignupActivity.this, SignupAccountTypeActivity.class);
                startActivity(intent);
            }
        });
    }
}