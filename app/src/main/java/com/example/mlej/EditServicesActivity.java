package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class EditServicesActivity extends AppCompatActivity {

    DatabaseHelper db;

    SharedPreferences preferences;
    int userId;

    boolean servicesModified = false;

    Button btnUpdateServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_services);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getInt("USERID",0);

        db = new DatabaseHelper(EditServicesActivity.this);

        CheckBox cboxServices1 = findViewById(R.id.cboxService1);
        CheckBox cboxServices2 = findViewById(R.id.cboxService2);
        CheckBox cboxServices3 = findViewById(R.id.cboxService3);
        CheckBox cboxServices4 = findViewById(R.id.cboxService4);
        CheckBox cboxServices5 = findViewById(R.id.cboxService5);
        CheckBox cboxServices6 = findViewById(R.id.cboxService6);
        CheckBox cboxServices7 = findViewById(R.id.cboxService7);
        CheckBox cboxServices8 = findViewById(R.id.cboxService8);
        CheckBox cboxServices9 = findViewById(R.id.cboxService9);
        CheckBox cboxServices10 = findViewById(R.id.cboxService10);
        btnUpdateServices = findViewById(R.id.btnUpdateServices);

        if (db.hasServices(userId, 1)) cboxServices1.setChecked(true);
        if (db.hasServices(userId, 2)) cboxServices2.setChecked(true);
        if (db.hasServices(userId, 3)) cboxServices3.setChecked(true);
        if (db.hasServices(userId, 4)) cboxServices4.setChecked(true);
        if (db.hasServices(userId, 5)) cboxServices5.setChecked(true);
        if (db.hasServices(userId, 6)) cboxServices6.setChecked(true);
        if (db.hasServices(userId, 7)) cboxServices7.setChecked(true);
        if (db.hasServices(userId, 8)) cboxServices8.setChecked(true);
        if (db.hasServices(userId, 9)) cboxServices9.setChecked(true);
        if (db.hasServices(userId, 10)) cboxServices10.setChecked(true);

        btnUpdateServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!servicesModified) finish();
                else{
                    for( int i=1; i<11; i++) db.removeServices(userId, i);
                    if(cboxServices1.isChecked()) db.addProviderServices(userId, 1);
                    if(cboxServices2.isChecked()) db.addProviderServices(userId, 2);
                    if(cboxServices3.isChecked()) db.addProviderServices(userId, 3);
                    if(cboxServices4.isChecked()) db.addProviderServices(userId, 4);
                    if(cboxServices5.isChecked()) db.addProviderServices(userId, 5);
                    if(cboxServices6.isChecked()) db.addProviderServices(userId, 6);
                    if(cboxServices7.isChecked()) db.addProviderServices(userId, 7);
                    if(cboxServices8.isChecked()) db.addProviderServices(userId, 8);
                    if(cboxServices9.isChecked()) db.addProviderServices(userId, 9);
                    if(cboxServices10.isChecked()) db.addProviderServices(userId, 10);

                    Toast.makeText(EditServicesActivity.this, "Services updated successfully", Toast.LENGTH_SHORT).show();
                    btnUpdateServices.setText("Close");
                    servicesModified = false;
                }
            }
        });

    }

    public void setServicesModified(View v){
        servicesModified = true;
        btnUpdateServices.setText("Update the services");
    }
}