package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnSLogin = findViewById(R.id.btnSLogin);
        Button btnSSignup = findViewById(R.id.btnSSignup);
        EditText txtSEmail = findViewById(R.id.etxtSEmailID);
        EditText txtSName = findViewById(R.id.etxtSUsername);
        EditText txtSPhone = findViewById(R.id.etxtSPhone);
        EditText txtSAddress = findViewById(R.id.etxtSAddress);
        EditText txtPostalCode = findViewById(R.id.etxtSPostalCode);
        EditText txtSPassword = findViewById(R.id.etxtSPassword);
        EditText txtSConfirmPassword = findViewById(R.id.etxtSPasswordConfirm);
        int accountType;

        if (getIntent().getStringExtra("accountType").equals("provider")) {
            accountType = 0;
        } else {
            accountType = 1;
        }

        //clicking on the Login button will go to the Login activity
        btnSLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if input is valid
                if (txtSEmail.getText().toString().isEmpty() || txtSName.getText().toString().isEmpty() || txtSPhone.getText().toString().isEmpty() || txtSAddress.getText().toString().isEmpty() || txtPostalCode.getText().toString().isEmpty() || txtSPassword.getText().toString().isEmpty() || txtSConfirmPassword.getText().toString().isEmpty()) {
                    // if any of the fields are empty, display error message
                    txtSEmail.setError("Please enter your email address");
                    txtSName.setError("Please enter your name");
                    txtSPhone.setError("Please enter your phone number");
                    txtSAddress.setError("Please enter your address");
                    txtPostalCode.setError("Please enter your postal code");
                    txtSPassword.setError("Please enter your password");
                    txtSConfirmPassword.setError("Please confirm your password");
                } else if (!txtSPassword.getText().toString().equals(txtSConfirmPassword.getText().toString())) {
                    // if the passwords do not match, display error message
                    txtSPassword.setError("Passwords do not match");
                    txtSConfirmPassword.setError("Passwords do not match");
                } else if (!txtSPhone.getText().toString().matches("[0-9]+")) {
                    txtSPhone.setError("Please enter a valid phone number");
                } else {
                    // add user to database
                    db = new DatabaseHelper(SignupActivity.this);
                    db.addUser(accountType, txtSName.getText().toString(), txtSEmail.getText().toString(), txtSPassword.getText().toString(), txtSPhone.getText().toString(), txtSAddress.getText().toString(), txtPostalCode.getText().toString());
                    // display toast message

                    Toast.makeText(SignupActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                    // empty all fields
                    txtSEmail.setText("");
                    txtSName.setText("");
                    txtSPhone.setText("");
                    txtSAddress.setText("");
                    txtPostalCode.setText("");
                    txtSPassword.setText("");
                    txtSConfirmPassword.setText("");
                }
            }
        });


    }
}