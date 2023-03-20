package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignupServicesActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_services);
        db = new DatabaseHelper(SignupServicesActivity.this);

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

        btnSSSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long l = db.addUserLong(0, intent.getStringExtra("SName"),intent.getStringExtra("SEmail"),intent.getStringExtra("SPassword"),
                        intent.getStringExtra("SPhone"), intent.getStringExtra("SAddress"), intent.getStringExtra("PostalCode"), etxtCompanyName.getText().toString());

                if (l>0){
                    if (cboxServices1.isChecked()) db.addProviderServices((int)l, 1);
                    if (cboxServices2.isChecked()) db.addProviderServices((int)l, 2);
                    if (cboxServices3.isChecked()) db.addProviderServices((int)l, 3);
                    if (cboxServices4.isChecked()) db.addProviderServices((int)l, 4);
                    if (cboxServices5.isChecked()) db.addProviderServices((int)l, 5);
                    if (cboxServices6.isChecked()) db.addProviderServices((int)l, 6);
                    if (cboxServices7.isChecked()) db.addProviderServices((int)l, 7);
                    if (cboxServices8.isChecked()) db.addProviderServices((int)l, 8);
                    if (cboxServices9.isChecked()) db.addProviderServices((int)l, 9);
                    if (cboxServices10.isChecked()) db.addProviderServices((int)l, 10);
                    Toast.makeText(SignupServicesActivity.this, "Service provider account created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupServicesActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}