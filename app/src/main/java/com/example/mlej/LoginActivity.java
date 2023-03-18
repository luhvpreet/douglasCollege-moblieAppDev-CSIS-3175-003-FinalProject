package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
                db = new DatabaseHelper(LoginActivity.this);
                // get the email and password from the text fields
                String email = txtLEmail.getText().toString();
                String password = txtLPassword.getText().toString();

                int type=0;
                int userId=0;

                // check if the email and password are valid
                if (db.verifyLogin(email, password)) {
                    // for now, display a toast message
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    //goes to either ServiceProviderHomeActivity or CustomerHomeActivity


                    Cursor cursor = db.getUserType(email);

                    if(cursor.getCount()>0){
                        while(cursor.moveToNext()){
                            userId = cursor.getInt(0);
                            type = cursor.getInt(1);
                        }
                    }
                    editor.putInt("UserType", type);
                    editor.putInt("UserId", userId);
                    editor.commit();

                    Intent intent;
                    if(type==0) intent = new Intent(LoginActivity.this, ServiceProviderHomeActivity.class);
                    else intent = new Intent(LoginActivity.this, CustomerHomeActivity.class);

                    startActivity(intent);
                } else {
                    // for now, display a toast message
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}