package com.example.mlej;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentViewHolder> {
    SharedPreferences preferences;
    DatabaseHelper db;
    private Context context;
    private List<AppointmentItemModel> appList;

    public AppointmentAdapter(Context context, List<AppointmentItemModel> appList){
        this.context = context;
        this.appList = appList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.appointment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        db = new DatabaseHelper(context);
        int userId = preferences.getInt("USERID",0);
        String[] dateTime = appList.get(position).getAppointDateTime().split(" ");
        if (db.getUserTypeById(userId) == 0) {
            holder.txtAppointItem.setText(
//                position+1+ ". with " +
                    position + 1 + ". " +
                            appList.get(position).getCustomerName() + " on " +
                            dateTime[0] + " at " + dateTime[1]);
        }
        else {
            holder.txtAppointItem.setText(
                    db.getCompanyNameByAppointmentId(appList.get(position).getAppointmentId()) + " on " +
                            dateTime[0] + " at " + dateTime[1]);
        }

        holder.btnEditAppDetails.setOnClickListener((view) -> {
            if (db.getAppointment(appList.get(position).getAppointmentId()) == null) {
                Toast.makeText(context, "Appointment not found", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(holder.btnEditAppDetails.getContext(), AppointmentDetails.class);
                intent.putExtra("appointmentId", appList.get(position).getAppointmentId());
                holder.btnEditAppDetails.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }
}
