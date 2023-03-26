package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentBook2 extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book2);
        setTitle("Book an Appointment");
        db = new DatabaseHelper(this);

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID
        String cName = getIntent().getStringExtra("cName"); // GET company Name

        TextView txtCompanyName = findViewById(R.id.txtAB2ComapnyName);
        TextView txtProviderName = findViewById(R.id.txtAB2ProviderName);
        txtCompanyName.setText(cName); //display company name
        txtProviderName.setText(db.getUserNameById(pID));  //display provider name


        //needs work
        ListView listView = findViewById(R.id.listViewAB2); //to display services


        Button btnNext = findViewById(R.id.btnAB2Next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentBook2.this, AppointmentBook3.class);
                intent.putExtra("cID", cID);
                intent.putExtra("pID", pID);
                // intent add services
                startActivity(intent);
            }
        });

    }
}