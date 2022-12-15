package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.BillModel;

import com.HomeManaging.homemanaging.R;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillHolder>{

    Context context;
    List<BillModel> billModels;

    public BillAdapter(Context context, List<BillModel> billModels) {
        this.context = context;
        this.billModels = billModels;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_design, parent, false);
        BillHolder billHolder = new BillHolder(view);
        return billHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {

        holder.Bill_Image.setImageResource(billModels.get(position).getBill_image());
        holder.Bill_deadline.setTag(billModels.get(position).getBill_date());
    }

    @Override
    public int getItemCount() {
        return billModels.size();
    }

    public class BillHolder extends RecyclerView.ViewHolder{

        ImageView Bill_Image, Bill_deadline;

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            Bill_Image = itemView.findViewById(R.id.Bill_image);
            Bill_deadline = itemView.findViewById(R.id.Bill_Date);
        }
    }
}
