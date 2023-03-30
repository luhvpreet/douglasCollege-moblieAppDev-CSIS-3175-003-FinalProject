package com.example.mlej;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesViewHolder> {

    View view;
    private Context context;
    private List<ServicesItemModel> servicesList;
    private List<Integer> selectedServices;

    double priceEstimates = 0.0d;

    SelectServicesListener selectServicesListener;

    public ServicesAdapter(Context context, List<ServicesItemModel> servicesList, List<Integer> selectedServices, SelectServicesListener selectServicesListener) {
        this.context = context;
        this.servicesList = servicesList;
        this.selectedServices = selectedServices;
        this.selectServicesListener = selectServicesListener;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.services_item, parent, false);
        return new ServicesViewHolder(view);
//        return new ServicesViewHolder(LayoutInflater.from(context).inflate(R.layout.services_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(servicesList!=null && servicesList.size()>0) {
            holder.cbxServices.setText(servicesList.get(position).getServicesName());
            if (selectedServices != null && selectedServices.contains(servicesList.get(position).getServicesId())) {
                holder.cbxServices.setChecked(true);
                selectServicesListener.addServices(servicesList.get(position).getServicesId());
            } else {
                holder.cbxServices.setChecked(false);
            }
            holder.cbxServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.cbxServices.isChecked()) {
                        priceEstimates += servicesList.get(position).getPrice();
                        selectServicesListener.addServices(servicesList.get(position).getServicesId());
                    } else {
                        priceEstimates -= servicesList.get(position).getPrice();
                        selectServicesListener.removeServices(servicesList.get(position).getServicesId());
                    }
                    selectServicesListener.onSelectedPriceChange(priceEstimates);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }
    
}

