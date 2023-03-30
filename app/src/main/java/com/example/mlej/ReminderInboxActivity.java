package com.example.mlej;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ReminderInboxActivity extends AppCompatActivity {
    List <ReminderItemModel> reminders;
    DatabaseHelper db;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_inbox);

        db = new DatabaseHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        ListView lvReminders = findViewById(R.id.reminderList);
        reminders = db.getReminders(preferences.getInt("USERID", 0), this);

        String[] reminderTitles;
        setTitle("Reminders");

        if (reminders == null) {
            reminderTitles = new String[]{"No reminders"};
        }
        else {
            reminderTitles = new String[reminders.size()];
            for (int i = 0; i < reminders.size(); i++) {
                reminderTitles[i] = reminders.get(i).getTitle();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reminderTitles);
        lvReminders.setAdapter(adapter);

        lvReminders.setOnItemClickListener((parent, view, position, id) -> {
            if (reminders != null) {
                ReminderItemModel reminder = reminders.get(position);
                startActivity(new Intent(ReminderInboxActivity.this, AppointmentDetails.class)
                        .putExtra("appointmentId", reminder.getId()));
            }
        });
        lvReminders.setOnItemLongClickListener((parent, view, position, id) -> {
            // a popup will appear asking if the user wants to delete the reminder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Reminder");
            builder.setMessage("Are you sure you want to delete this reminder?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                ReminderItemModel reminder = reminders.get(position);
                db.removeReminder(reminder.getReminderId());
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                // do nothing
            });
            builder.show();
            return true;
        });
    }
}