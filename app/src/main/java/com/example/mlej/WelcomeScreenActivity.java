package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        TextView textView5 = findViewById(R.id.textView5);

        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("isLogout", false)) {
            textView5.setText("See you again soon!");
        }
        else textView5.setText("Welcome to MLEJ");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    finish();
                    startActivity(new Intent(WelcomeScreenActivity.this,
                            LoginSignupActivity.class));
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5000);

    }
}