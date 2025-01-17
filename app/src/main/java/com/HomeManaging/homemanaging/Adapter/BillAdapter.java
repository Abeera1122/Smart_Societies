package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.BillDetailsActivity;
import com.HomeManaging.homemanaging.Model.Bill;
import com.HomeManaging.homemanaging.Model.BillModel;

import com.HomeManaging.homemanaging.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillHolder> {

    Context context;
    List<Bill> billModels;

    public BillAdapter(Context context, List<Bill> billModels) {
        this.context = context;
        this.billModels = billModels;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_design, parent, false);
        BillHolder billHolder = new BillHolder(view);
        return billHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {

//        holder.Bill_Image.setImageResource(billModels.get(position).getImageUrl());
        holder.Bill_deadline.setText(billModels.get(position).getDeadLine());

        Glide.with(context).load(billModels.get(position).getImageUrl()).into(holder.Bill_Image);

        holder.Bill_CardView.setOnClickListener(view -> {
            BillDetailsActivity.loadActivity(context, billModels.get(position));
//                context.startActivity(new Intent(context, BillDetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return billModels.size();
    }

    public class BillHolder extends RecyclerView.ViewHolder {

        ImageView Bill_Image;
        TextView Bill_deadline;
        CardView Bill_CardView;

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            Bill_Image = (ImageView) itemView.findViewById(R.id.Bill_image);
            Bill_deadline = itemView.findViewById(R.id.Bill_Date);
            Bill_CardView = itemView.findViewById(R.id.Bill_CardView);
        }
    }
}
