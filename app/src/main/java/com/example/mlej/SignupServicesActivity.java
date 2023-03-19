package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignupServicesActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_services);

        EditText etxtCompanyName = findViewById(R.id.etxtCompanyName);
        CheckBox cboxServices1 = findViewById(R.id.cboxService1);
        CheckBox cboxServices2 = findViewById(R.id.cboxService2);
        CheckBox cboxServices3 = findViewById(R.id.cboxService3);
        CheckBox cboxServices4 = findViewById(R.id.cboxService4);
        CheckBox cboxServices5 = findViewById(R.id.cboxService5);
        CheckBox cboxServices6 = findViewById(R.id.cboxService6);
        CheckBox cboxServices7 = findViewById(R.id.cboxService7);
        CheckBox cboxServices8 = findViewById(R.id.cboxService8);
        CheckBox cboxServices9 = findViewById(R.id.cboxService9);
        CheckBox cboxServices10 = findViewById(R.id.cboxService10);
        Button btnSSSignup = findViewById(R.id.btnSSSignup);

        Intent intent = getIntent();

        //WIP:
        //get all the info back from the intent from SignupActivity
        //intent.putExtra("SName", txtSName.getText().toString());
        //intent.putExtra("SEmail", txtSEmail.getText().toString());
        //intent.putExtra("SPassword", txtSPassword.getText().toString());
        //intent.putExtra("SPhone", txtSPhone.getText().toString());
        //intent.putExtra("SAddress", txtSAddress.getText().toString());
        //intent.putExtra("PostalCode", txtPostalCode.getText().toString());



    }
}