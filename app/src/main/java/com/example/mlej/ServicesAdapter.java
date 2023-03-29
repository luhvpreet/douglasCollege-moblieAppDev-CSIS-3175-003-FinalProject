package com.example.mlej;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesViewHolder> {

    private Context context;
    private List<ServicesItemModel> servicesList;

    public ServicesAdapter(Context context, List<ServicesItemModel> servicesList){
        this.context = context;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServicesViewHolder(LayoutInflater.from(context).inflate(R.layout.services_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {
        holder.cbxServices.setText(servicesList.get(position).getServicesName());

    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }
}

