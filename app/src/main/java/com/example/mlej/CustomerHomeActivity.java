package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerHomeActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        int userId = preferences.getInt("USERID",0);

        TextView txtWelcome = findViewById(R.id.txtCHUserName);
        String username = db.getUserNameById(userId);

        txtWelcome.setText("Welcome " + username + "!");

        Button btnEditProfile = findViewById(R.id.btnCHEditProfile);
        Button btnViewProfile = findViewById(R.id.btnCHViewProfile);
        Button btnSearchProvider = findViewById(R.id.btnCHSearch);
        Button btnViewReminders = findViewById(R.id.btnCHReminder);

        btnSearchProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this, ProviderSearchActivity.class);
                startActivity(intent);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this, ProfileEditActivity.class);
                startActivity(intent);
            }
        });

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this, ProfileViewActivity.class);
                intent.putExtra("id", userId);
                intent.putExtra("name", username);
                startActivity(intent);
            }
        });

        btnViewReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this, ReminderInboxActivity.class);
                startActivity(intent);
            }
        });

        // implement logout
        Button btnLogout = findViewById(R.id.btnCHLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomeActivity.this, WelcomeScreenActivity.class);
                intent.putExtra("isLogout", true);
                editor.remove("USERID");
                editor.commit();
                startActivity(intent);
            }
        });
    }
}