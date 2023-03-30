package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileViewActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        db = new DatabaseHelper(this);
        int id = getIntent().getIntExtra("id",0);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtPostal = findViewById(R.id.txtPostal);

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