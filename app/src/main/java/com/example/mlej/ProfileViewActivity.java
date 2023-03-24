package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ProfileViewActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        db = new DatabaseHelper(this);
        int id = getIntent().getIntExtra("id",0);

        TextView txtName = findViewById(R.id.profileName);
        TextView txtEmail = findViewById(R.id.profileEmail);
        TextView txtPhone = findViewById(R.id.profilePhone);
        TextView txtAddress = findViewById(R.id.profileAddress);
        TextView txtPostal = findViewById(R.id.profilePostal);

        txtName.setText(db.getUserNameById(id));
        txtEmail.setText(db.getUserEmail(id));
        txtPhone.setText(db.getUserPhone(id));
        txtAddress.setText(db.getUserAddress(id));
        txtPostal.setText(db.getUserPostalCode(id));

        // set title
        String name = getIntent().getStringExtra("name");
        setTitle(name + "'s Profile");
    }
}