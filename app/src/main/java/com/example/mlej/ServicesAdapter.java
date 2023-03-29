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

    double priceEstimates = 0.0d;

    SelectServicesListender selectServicesListender;

    public ServicesAdapter(Context context, List<ServicesItemModel> servicesList, SelectServicesListender selectServicesListender) {
        this.context = context;
        this.servicesList = servicesList;
        this.selectServicesListender = selectServicesListender;
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
            holder.cbxServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.cbxServices.isChecked()) {
                        priceEstimates += servicesList.get(position).getPrice();
                        selectServicesListender.addServices(servicesList.get(position).getServicesId());
                    } else {
                        priceEstimates -= servicesList.get(position).getPrice();
                        selectServicesListender.removeServices(servicesList.get(position).getServicesId());
                    }
                    selectServicesListender.onSelectedPriceChange(priceEstimates);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }
}

