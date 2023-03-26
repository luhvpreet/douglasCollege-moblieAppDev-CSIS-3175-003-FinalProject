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

        // display customer & service provider location
        TextView txtPLocation = findViewById(R.id.txtAB3DropoffLocation);
        TextView txtCLocation = findViewById(R.id.txtAB3PickupLocation);
        txtPLocation.setText("OUR location : "+ db.getUserAddress(pID));
        txtCLocation.setText("YOUR location : "+ db.getUserAddress(cID));

        // check which if the option is checked
        RadioButton radbtnD = findViewById(R.id.radbtnDropOff);


        if(radbtnD.isChecked()){
            pickupordropoff = "0"; //means drop off customer will drop the car
        }
        else{
            pickupordropoff = "1"; //means provider will pick up the car
        }

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
                Intent intent = new Intent(AppointmentBook3.this, AppointmentBook4.class);
                intent.putExtra("cID", cID);
                intent.putExtra("pID", pID);
                //intent add services
                intent.putExtra("pickordrop", pickupordropoff);
                intent.putExtra("date", appointmentDate);
                intent.putExtra("time", appointmentTime);
                startActivity(intent);
            }
        });


    }

    private void openDate(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                appointmentDate = String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(day);
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
                appointmentTime = String.valueOf(hour)+":"+String.valueOf(min);
                TextView txtTime =  findViewById(R.id.txtAB3Time);
                txtTime.setText(appointmentTime);
            }
        }, 13, 3, true);
        dialog.show();
    }
}