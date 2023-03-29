package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AppointmentBook4 extends AppCompatActivity {

    DatabaseHelper db;
    String serviceOption;

    DecimalFormat currency;

    //Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book4);
        setTitle("Book an Appointment");
        db = new DatabaseHelper(this);
        currency = new DecimalFormat("$###,###.##");

        String txtServices = "";

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID

        int[] s = new int[10];
        for (int i=0; i<s.length; i++){
            s[i] = getIntent().getIntExtra(Integer.toString((i+1)),0); // GET serviceID#1 to #10
            System.out.println("1st: s[i]" + s[i]);
        }

        // GET serviceID#1 to #10
        for (int i=0; i<s.length; i++){


            if(s[i]!=0){
                System.out.println("2nd: s[i]" + s[i]);
                txtServices += db.getServicesName(s[i]) + "\n";
            }
        }

        double priceEstimates = getIntent().getDoubleExtra("priceEstimates", 0);

        //intent add services
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String pickordrop = getIntent().getStringExtra("pickordrop");
        if (pickordrop.equals("0")) {
            serviceOption = "Pick Up";
        }
        if (pickordrop.equals("1")){
            serviceOption = "Drop Off";
        }

        TextView txtDetails = findViewById(R.id.txtAB4Details);
        txtDetails.setText("Customer Name : "+db.getUserNameById(cID)+"\n"
                +"Customer Address : "+db.getUserAddress(cID)+"\n"
                + "Service Option : "+serviceOption+ "\n"
                + "Appointment Date : "+date+"\n"
                + "Appointment Time : "+time+"\n"
                + "Service Provider Name : "+db.getUserNameById(pID)+"\n"
                + "Service Center Address : "+db.getUserAddress(pID)+"\n\n"
                + "Services Requested :\n"
                + txtServices + "\n\n"
                + "Estimated total is " + currency.format(priceEstimates));

    }
}