package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ViewServicesActivity extends AppCompatActivity {

    DatabaseHelper db;

    SharedPreferences preferences;
    int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getInt("USERID",0);

        db = new DatabaseHelper(ViewServicesActivity.this);

        String[] services = db.getServicesFromProviderID(userId);

        TextView txtServicesProviding = findViewById(R.id.txtServicesProviding);
        StringBuilder str = new StringBuilder();
        str.append("You are now providing the following services:\n\n");

        for(int i=0; i<services.length; i++)
        {
            str.append(services[i]+"\n");
        }

        txtServicesProviding.setText(str);

    }
}