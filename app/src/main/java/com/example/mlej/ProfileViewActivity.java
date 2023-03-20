package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ProfileViewActivity extends AppCompatActivity {
    DatabaseHelper db;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        TextView txtName = findViewById(R.id.profileName);
        TextView txtEmail = findViewById(R.id.profileEmail);
        TextView txtPhone = findViewById(R.id.profilePhone);
        TextView txtAddress = findViewById(R.id.profileAddress);
        TextView txtPostal = findViewById(R.id.profilePostal);

        txtName.setText(db.getUserNameById(preferences.getInt("USERID",0)));
        txtEmail.setText(db.getUserEmail(preferences.getInt("USERID",0)));
        txtPhone.setText(db.getUserPhone(preferences.getInt("USERID",0)));
        txtAddress.setText(db.getUserAddress(preferences.getInt("USERID",0)));
        txtPostal.setText(db.getUserPostalCode(preferences.getInt("USERID",0)));
    }
}