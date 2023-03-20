package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SignupAccountTypeActivity extends AppCompatActivity {

    Button btnSANext;
    RadioButton radbtnProvider;
    RadioButton radbtnTaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_account_type);

        btnSANext = findViewById(R.id.btnSANext);
        radbtnProvider = findViewById(R.id.radbtnProvider);
        radbtnTaker = findViewById(R.id.radbtnTaker);

        //Clicking to the Next button will go to either the Taker or Provider signup page depending on the radio button choice
        btnSANext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radbtnProvider.isChecked() || radbtnTaker.isChecked()){
                    // pass the account type to the next activity
                    Intent intent = new Intent(SignupAccountTypeActivity.this, SignupActivity.class);
                    intent.putExtra("accountType", radbtnProvider.isChecked() ? "provider" : "taker");
                    startActivity(intent);
                }
            }
        });
    }

    public void clickProviderImage(View v){
        radbtnProvider.setChecked(true);
        radbtnTaker.setChecked(false);
    }

    public void clickTakerImage(View v){
        radbtnProvider.setChecked(false);
        radbtnTaker.setChecked(true);
    }

}