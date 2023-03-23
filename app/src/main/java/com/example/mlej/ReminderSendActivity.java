package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReminderSendActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_send);

        Button btnTest = findViewById(R.id.btnTest);
        db = new DatabaseHelper(this);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.addReminder(1, 2, 1)) {
                    Toast.makeText(ReminderSendActivity.this, "Reminder added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ReminderSendActivity.this, "Reminder not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}