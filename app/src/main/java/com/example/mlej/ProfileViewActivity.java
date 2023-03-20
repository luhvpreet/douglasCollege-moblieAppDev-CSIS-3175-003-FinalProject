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

        TextView txtName = findViewById(R.id.txtName);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtPostal = findViewById(R.id.txtPostal);

        txtName.setText(db.getUserNameById(preferences.getInt("USERID",0)));
        txtEmail.setText(db.getUserEmail(preferences.getInt("USERID",0)));
        txtPhone.setText(db.getUserPhone(preferences.getInt("USERID",0)));
        txtAddress.setText(db.getUserAddress(preferences.getInt("USERID",0)));
        txtPostal.setText(db.getUserPostalCode(preferences.getInt("USERID",0)));
    }
}