package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppointmentEditActivity extends AppCompatActivity implements SelectServicesListener {

    DatabaseHelper db;
    String pickupordropoff;
    String appointmentDate;
    String appointmentTime;
    List<ServicesItemModel> servicesList;
    List<Integer> selectedServices = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_edit);
        setTitle("Edit the Appointment");

        db = new DatabaseHelper(this);

        int cID = getIntent().getIntExtra("userId",0); // GET customer ID
        int pID = getIntent().getIntExtra("mainUserId",0); // GET provider ID
        int aID = getIntent().getIntExtra("appointmentId", 0); // GET company Name


        //recycler view starts ---------------------------------------------------------------------
        RecyclerView serRecyclerView;
        ServicesAdapter servicesAdapter;
        servicesList = new ArrayList<>();
        servicesList = db.getServicesFromProviderId(pID);
        if(servicesList != null) {
            serRecyclerView = findViewById(R.id.AAErecyclerServicesView);
            serRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            servicesAdapter = new ServicesAdapter(this, servicesList, this);
            serRecyclerView.setAdapter(servicesAdapter);
        }

        // check which of the option is checked
        RadioButton radbtnPickUp = findViewById(R.id.AAEradbtnPickUp);
        RadioButton radbtnDropOff = findViewById(R.id.AAEradbtnDropOff);
        String type = db.getAppointmentType(aID);
        if(type.equals("0")){
            radbtnDropOff.setChecked(true);
        }
        else if(type.equals("1")){
            radbtnPickUp.setChecked(true);
        }
        //recycler view ends -----------------------------------------------------------------------


        // pick date and time starts ---------------------------------------------------------------
        TextView oldTimeDate = findViewById(R.id.txtAAEOldDateTime);
        oldTimeDate.setText("Old Date and Time : "+db.getAppointmentDateTime(aID));

        Button btnPickDate = findViewById(R.id.btnAAEPickDate);
        Button btnPickTime = findViewById(R.id.btnAAEPickTime);

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

        TextView txtDate =  findViewById(R.id.txtAAEDate);  //get date
        TextView txtTime =  findViewById(R.id.txtAAETime);  //get time
        // pick date and time ends -----------------------------------------------------------------

        Button btnConfirmandEdit = findViewById(R.id.btnAAEConfirmAndEdit);
        btnConfirmandEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radbtnDropOff.isChecked()) {
                    pickupordropoff = "1";
                } else {
                    pickupordropoff = "0";
                }
                boolean dateTimeChanged = false;
                if (appointmentDate != null && appointmentTime != null) {
                    appointmentDate = appointmentDate + " " + appointmentTime;
                    dateTimeChanged = true;
                }
                else if(appointmentDate == null && appointmentTime != null){
                    btnPickDate.setError("Please pick a date");
                }
                else if(appointmentDate != null && appointmentTime == null){
                    btnPickDate.setError("Please pick a time");
                }
                db.updateAppointment(aID, dateTimeChanged ? appointmentDate : db.getAppointmentDateTime(aID), pickupordropoff);
                db.removeAllServicesFromAppointment(aID);
                for (int i = 0; i < selectedServices.size(); i++) {
                    db.addAppointmentServices(aID, selectedServices.get(i));
                }
                Toast.makeText(AppointmentEditActivity.this, "Appointment Edited", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AppointmentEditActivity.this, AppointmentListActivity.class);
                intent.putExtra("upcoming", true);
                startActivity(intent);
            }
        });


    }

    private void openDate(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                DecimalFormat df = new DecimalFormat("00");
                appointmentDate = df.format(year) + "-" + df.format(month+1) + "-" + df.format(day);
                TextView txtDate =  findViewById(R.id.txtAAEDate);
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
                TextView txtTime =  findViewById(R.id.txtAAETime);
                txtTime.setText(appointmentTime);
            }
        }, 12, 0, true);
        dialog.show();
    }

    @Override
    public void onSelectedPriceChange(double priceEstimates) {

    }

    @Override
    public void addServices(int servicesId) {
        selectedServices.add(servicesId);
    }

    @Override
    public void removeServices(int servicesId) {
        // get the index of the service
        int index = selectedServices.indexOf(servicesId);
        selectedServices.remove(index);
    }
}