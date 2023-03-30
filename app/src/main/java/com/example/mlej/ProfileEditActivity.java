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

public class ProfileEditActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        db = new DatabaseHelper(this);
        // Name edit has been removed because it can cause trouble for home activity
        // In real life, names generally can not be edited by the user
        // Therefore this is not a concern at the moment
        EditText txtName = findViewById(R.id.profileEditUserName);
        EditText txtEmail = findViewById(R.id.profileEditEmail);
        EditText txtPhone = findViewById(R.id.profileEditPhone);
        EditText txtAddress = findViewById(R.id.profileEditAddress);
        EditText txtPostal = findViewById(R.id.profileEditPostal);

        //if provider is editing customer profile from provider side
        int mainUserId = getIntent().getIntExtra("mainUserId", 0);
        //if respective users are editing their own respective profiles
        int userId = getIntent().getIntExtra("userId", 0);


        txtName.setText(db.getUserNameById(userId));
        txtEmail.setText(db.getUserEmail(userId));
        txtPhone.setText(db.getUserPhone(userId));
        txtAddress.setText(db.getUserAddress(userId));
        txtPostal.setText(db.getUserPostalCode(userId));

        Button btnSaveProfile = findViewById(R.id.btnSaveProfile);

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name = db.getUserNameById(userId);
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String postal = txtPostal.getText().toString();

                db.updateUser(userId, name, email, phone, address, postal);
                Toast.makeText(ProfileEditActivity.this, "Profile updated!", Toast.LENGTH_SHORT).show();
                finish();
                if (db.getUserTypeById(userId) == 0 || db.getUserTypeById(mainUserId) == 0) {
                    // if the user is a provider, go to the ProviderHome activity
                    Intent intent = new Intent(ProfileEditActivity.this, ServiceProviderHomeActivity.class);
                    startActivity(intent);
                } else {
                    // if the user is a customer, go to the CustomerHome activity
                    Intent intent = new Intent(ProfileEditActivity.this, CustomerHomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}