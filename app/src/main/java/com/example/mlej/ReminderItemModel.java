package com.example.mlej;

import android.content.Context;

public class ReminderItemModel {
    private int reminderId;
    private int senderId;
    private int appointmentId;
    private String title;
    DatabaseHelper db;

    public ReminderItemModel(int reminderId, int senderId, int appointmentId, Context context) {
        db = new DatabaseHelper(context);
        this.reminderId = reminderId;
        this.senderId = senderId;
        this.appointmentId = appointmentId;
        this.title = "Upcoming Appointment\n\n" + db.getCompanyName(senderId) + "\n\n" + db.getAppointmentDateTime(appointmentId);
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return appointmentId;
    }
    public int getReminderId() {
        return reminderId;
    }
}
