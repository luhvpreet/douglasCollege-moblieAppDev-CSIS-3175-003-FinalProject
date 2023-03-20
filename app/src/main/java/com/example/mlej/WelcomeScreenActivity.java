package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreenActivity extends AppCompatActivity {
    SharedPreferences preferences;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        TextView textView5 = findViewById(R.id.textView5);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("isLogout", false)) {
            textView5.setText("See you again soon!");
        }
        else textView5.setText("Welcome to MLEJ");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    finish();
                    // if userId exists, go to home, else go to login or signup
                    if (preferences.contains("USERID") && getIntent().getBooleanExtra("isLogout", false) == false) {
                        int userId = preferences.getInt("USERID", 0);
                        if (db.getUserTypeById(userId) == 0) {
                            startActivity(new Intent(WelcomeScreenActivity.this,
                                    ServiceProviderHomeActivity.class));
                        } else {
                            startActivity(new Intent(WelcomeScreenActivity.this,
                                    CustomerHomeActivity.class));
                        }
                    }
                    else {
                        startActivity(new Intent(WelcomeScreenActivity.this,
                                LoginSignupActivity.class));
                    }
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5000);

    }
}