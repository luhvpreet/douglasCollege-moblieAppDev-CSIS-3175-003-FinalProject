package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBook2 extends AppCompatActivity implements SelectServicesListener {

    DatabaseHelper dbh;
    Intent intent;

    String txtOutput;
    int numberOfServicesSelected;
    double priceEstimates;

    DecimalFormat currency;
    TextView txtEstimates;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book2);
        setTitle("Book an Appointment");
        txtEstimates = findViewById(R.id.txtEstimates);
        dbh = new DatabaseHelper(this);
        currency = new DecimalFormat("$###,###.##");
        intent = new Intent(AppointmentBook2.this, AppointmentBook3.class);
        btnNext = findViewById(R.id.btnAB2Next);
        btnNext.setClickable(false);

        RecyclerView serRecyclerView;
        List<ServicesItemModel> servicesList;
        ServicesAdapter servicesAdapter;

        int cID = getIntent().getIntExtra("cID",0); // GET customer ID
        int pID = getIntent().getIntExtra("pID",0); // GET provider ID
        String cName = getIntent().getStringExtra("cName"); // GET company Name

        TextView txtCompanyName = findViewById(R.id.txtAB2ComapnyName);
        TextView txtProviderName = findViewById(R.id.txtAB2ProviderName);
        txtCompanyName.setText(cName); //display company name
        txtProviderName.setText(dbh.getUserNameById(pID));  //display provider name

        servicesList = new ArrayList<>();
        servicesList = dbh.getServicesFromProviderId(pID);

        if(servicesList != null) {
            serRecyclerView = findViewById(R.id.recyclerServicesView);
            serRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            servicesAdapter = new ServicesAdapter(this, servicesList, this);
            serRecyclerView.setAdapter(servicesAdapter);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent.putExtra("cID", cID);
                intent.putExtra("pID", pID);

                intent.putExtra("priceEstimates", priceEstimates);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onSelectedPriceChange(double priceEstimates) {
        this.priceEstimates = priceEstimates;
        if(numberOfServicesSelected==0){
            txtOutput = "Please select the services you want.";
            btnNext.setClickable(false);
        } else {
            txtOutput = "You have selected " + numberOfServicesSelected + " services and the estimated total is " + currency.format(priceEstimates);
            btnNext.setClickable(true);
        }
        txtEstimates.setText(txtOutput);
    }

    @Override
    public void addServices(int servicesId) {
        numberOfServicesSelected++;
        //1 means servicesId#1 , etc
        //intent.putExtra(Integer.toString( (servicesId+1) ) , servicesId );
        intent.putExtra(Integer.toString( servicesId ) , 1 ); //1 means YES
    }

    @Override
    public void removeServices(int servicesId) {
        numberOfServicesSelected--;
        //1 means servicesId#1 , etc
        intent.removeExtra(Integer.toString(servicesId));
    }
}