package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AppointmentBook4 extends AppCompatActivity {

    DatabaseHelper db;
    String serviceOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book4);
        setTitle("Book an Appointment");
        db = new DatabaseHelper(this);

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID
        //intent add services
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String pickordrop = getIntent().getStringExtra("pickordrop");
        if (pickordrop.equals("0")) {
            serviceOption = "Drop Off";
        }
        if (pickordrop.equals("1")){
            serviceOption = "Pick Up";
        }

        TextView txtDetails = findViewById(R.id.txtAB4Details);
        txtDetails.setText("Customer Name : "+db.getUserNameById(cID)+"\n"
                +"Customer Address : "+db.getUserAddress(cID)+"\n"
                + "Service Option : "+serviceOption+ "\n"
                + "Appointment Date : "+date+"\n"
                + "Appointment Time : "+time+"\n"
                + "Service Provider Name : "+db.getUserNameById(pID)+"\n"
                + "Service Center Address : "+db.getUserAddress(pID)+"\n");
    }
}