package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AppointmentBook4 extends AppCompatActivity {

    DatabaseHelper db;
    String serviceOption;
    DecimalFormat currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book4);
        setTitle("Book an Appointment");
        db = new DatabaseHelper(this);
        currency = new DecimalFormat("$###,###.##");

        Button btnAB4ConfirmAndBook = findViewById(R.id.btnAB4ConfirmAndBook);

        String txtServices = "";

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID

        int[] s = new int[10];
        for (int i=0; i<s.length; i++){
            s[i] = getIntent().getIntExtra(Integer.toString((i+1)),0); // GET serviceID#1 to #10
        }

        // GET serviceID#1 to #10
        for (int i=0; i<s.length; i++){

            if(s[i]==1){
                txtServices += db.getServicesName(i+1) + "\n";
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
                + txtServices + "\n"
                + "Estimated total is " + currency.format(priceEstimates));

        btnAB4ConfirmAndBook.setOnClickListener(new View.OnClickListener() {

            boolean clicked =false;
            @Override
            public void onClick(View v) {
                if (!clicked){
                    int appointmentId = (int)db.addAppointment(cID, pID, date+" "+time, Integer.parseInt(pickordrop));
                    for(int i=0; i<s.length; i++){
                        if(s[i] == 1) db.addAppointmentServices(appointmentId, i+1); //if i is zero, that means servicesID=1
                    }

                    if (appointmentId!=-1) Toast.makeText(AppointmentBook4.this, "Appointment is confirmed!", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(AppointmentBook4.this, "System error! Please try again later!", Toast.LENGTH_SHORT).show();
                    }

                    btnAB4ConfirmAndBook.setText("Back to Homepage");
                    clicked = true;
                }
                else {

                    Intent intent = new Intent(AppointmentBook4.this, CustomerHomeActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}