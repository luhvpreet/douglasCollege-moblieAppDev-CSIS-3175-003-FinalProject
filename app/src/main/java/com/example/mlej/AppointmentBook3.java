package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AppointmentBook3 extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book3);
        setTitle("Book an Appointment");
        db = new DatabaseHelper(this);

        TextView txtPLocation = findViewById(R.id.txtAB3DropoffLocation);
        TextView txtCLocation = findViewById(R.id.txtAB3PickupLocation);

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID

        txtPLocation.setText("OUR LOCATION : "+ db.getUserAddress(pID));
        txtCLocation.setText("YOUR LOCATION : "+ db.getUserAddress(cID));


    }
}