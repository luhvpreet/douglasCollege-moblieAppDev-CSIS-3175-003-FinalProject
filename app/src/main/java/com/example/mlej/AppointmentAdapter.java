package com.example.mlej;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentViewHolder> {

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
        holder.txtAppointItem.setText(
//                position+1+ ". with " +
                position + 1 + ". " +
                appList.get(position).getCustomerName() + " on " +
                appList.get(position).getAppointDateTime());

        holder.btnEditAppDetails.setOnClickListener((view) -> {
            Intent intent = new Intent(holder.btnEditAppDetails.getContext(), AppointmentDetails.class);
            intent.putExtra("appointmentId", appList.get(position).getAppointmentId());
            holder.btnEditAppDetails.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }
}
