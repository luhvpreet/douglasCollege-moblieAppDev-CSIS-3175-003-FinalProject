package com.example.mlej;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesViewHolder extends RecyclerView.ViewHolder {

    public CardView main_container;
    public CheckBox cbxServices;

    public ServicesViewHolder(@NonNull View itemView) {
        super(itemView);

        main_container = itemView.findViewById(R.id.main_container);
        cbxServices = itemView.findViewById(R.id.servicesCheckBox);

    }
}