package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SignupAccountTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_account_type);

        Button btnSANext = findViewById(R.id.btnSANext);
        RadioButton radbtnProvider = findViewById(R.id.radbtnTaker);
        RadioButton radbtnTaker = findViewById(R.id.radbtnTaker);

        //Clicking to the Next button will go to either the Taker or Provider signup page depending on the radio button choice
        btnSANext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radbtnProvider.isChecked()){
                    Intent intent = new Intent(SignupAccountTypeActivity.this, SignupActivity.class);
                    startActivity(intent);
                } else if(radbtnTaker.isChecked()){
                    //go to the provider signup activity, not yet created
                }
            }
        });

    }
}