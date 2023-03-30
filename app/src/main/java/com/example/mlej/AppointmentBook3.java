package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

public class AppointmentBook3 extends AppCompatActivity {

    DatabaseHelper db;
    String pickupordropoff;
    String appointmentDate;
    String appointmentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book3);
        setTitle("Book an Appointment");
        db = new DatabaseHelper(this);

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID

        // GET serviceID#1 to #10
        int[] s = new int[10];
        for (int i=0; i<s.length; i++){
            s[i] = getIntent().getIntExtra(Integer.toString((i+1)),0); // GET serviceID#1 to #10
        }

        // Get priceEstimates and put it back into new Intent
        double priceEstimates = getIntent().getDoubleExtra("priceEstimates", 0);

        // display customer & service provider location
        TextView txtPLocation = findViewById(R.id.txtAB3DropoffLocation);
        TextView txtCLocation = findViewById(R.id.txtAB3PickupLocation);
        txtPLocation.setText("OUR location : "+ db.getUserAddress(pID));
        txtCLocation.setText("YOUR location : "+ db.getUserAddress(cID));

        // check which if the option is checked
        RadioButton radbtnPickUp = findViewById(R.id.radbtnPickUp);
        RadioButton radbtnDropOff = findViewById(R.id.radbtnDropOff);

        // pick date and time starts ---------------------------------------------------------------
        Button btnPickDate = findViewById(R.id.btnAB3PickDate);
        Button btnPickTime = findViewById(R.id.btnAB3PickTime);
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDate();
            }
        });
        btnPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTime();
            }
        });
        // pick date and time ends -----------------------------------------------------------------


        // confirm the appointment
        Button btnBookAppointment = findViewById(R.id.btnAB3BookAppointment);
        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = true;
                if (!radbtnPickUp.isChecked() && !radbtnDropOff.isChecked()){
                    radbtnPickUp.setError("Please select one of the options");
                    radbtnDropOff.setError("Please select one of the options");
                    valid = false;
                }
                if (appointmentDate == null){
                    btnPickDate.setError("Please select a date");
                    valid = false;
                }
                if (appointmentTime == null){
                    btnPickTime.setError("Please select a time");
                    valid = false;
                }

                if (valid){
                    String[] date = appointmentDate.split("-");
                    int year = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int day = Integer.parseInt(date[2]);
                    String[] time = appointmentTime.split(":");
                    int hour = Integer.parseInt(time[0]);
                    int minute = Integer.parseInt(time[1]);

                    // if the date is before today
                    Calendar appointmentDateTime = Calendar.getInstance();
                    appointmentDateTime.set(year, month, day, hour, minute);
                    Calendar now = Calendar.getInstance();

                    if (appointmentDateTime.before(now)){
                        btnPickDate.setError("Selected date or time is in the past");
                        btnPickTime.setError("Selected date or time is in the past");
                    } else {
                        if (radbtnPickUp.isChecked()){
                            pickupordropoff = "0";
                        } else if (radbtnDropOff.isChecked()){
                            pickupordropoff = "1";
                        }

                        Intent intent = new Intent(AppointmentBook3.this, AppointmentBook4.class);
                        intent.putExtra("cID", cID);
                        intent.putExtra("pID", pID);
                        intent.putExtra("priceEstimates", priceEstimates);
                        intent.putExtra("pickordrop", pickupordropoff);
                        intent.putExtra("date", appointmentDate);
                        intent.putExtra("time", appointmentTime);

                        //insert all servicesId into the new intent
                        for(int i=0; i<s.length; i++){
                            if(s[i]==1) intent.putExtra(Integer.toString(i+1), 1);
                        }

                        startActivity(intent);
                    }
                }
            }
        });


    }

    private void openDate(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                DecimalFormat df = new DecimalFormat("00");
                appointmentDate = df.format(year) + "-" + df.format(month+1) + "-" + df.format(day);
                TextView txtDate =  findViewById(R.id.txtAB3Date);
                txtDate.setText(appointmentDate);
            }
        }, 2023, 3, 1);
        dialog.show();
    }
    private void openTime(){
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                // format the time to hh:mm
                DecimalFormat df = new DecimalFormat("00");
                appointmentTime = df.format(hour) + ":" + df.format(min);
                // appointmentTime = String.valueOf(hour)+":"+String.valueOf(min);
                TextView txtTime =  findViewById(R.id.txtAB3Time);
                txtTime.setText(appointmentTime);
            }
        }, 12, 0, true);
        dialog.show();
    }
}