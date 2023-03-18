package com.example.mlej;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentViewHolder extends RecyclerView.ViewHolder {

    public TextView txtAppointItem;
    public CardView main_container;
    public Button btnEditAppDetails;

    public AppointmentViewHolder(@NonNull View itemView) {
        super(itemView);

        txtAppointItem = itemView.findViewById(R.id.txtAppointItem);
        main_container = itemView.findViewById(R.id.main_container);
        btnEditAppDetails = itemView.findViewById(R.id.btnEditAppointItem);

    }
}
