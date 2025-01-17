package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.BillHistoryModel;
import com.HomeManaging.homemanaging.Model.Payment;
import com.HomeManaging.homemanaging.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class BillHistoryAdapter extends RecyclerView.Adapter<BillHistoryAdapter.BillHistoryHolder> {

    Context context;
    List<Payment> billHistoryModels;

    public BillHistoryAdapter(Context context, List<Payment> billHistoryModels) {
        this.context = context;
        this.billHistoryModels = billHistoryModels;
    }

    @NonNull
    @Override
    public BillHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.bill_history_design, parent, false);
        BillHistoryHolder billHistoryHolder = new BillHistoryHolder(view);
        return billHistoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillHistoryHolder holder, int position) {

//        holder.BillHistory_Image.setImageResource(billHistoryModels.get(position).getBillHistory_Image());
        holder.BillHistory_status.setText(billHistoryModels.get(position).getStatus());
        holder.BillHistory_Date.setText(billHistoryModels.get(position).getPaymentDate());
        Glide.with(context).load(billHistoryModels.get(position).getImageUrl()).into(holder.BillHistory_Image);

    }

    @Override
    public int getItemCount() {
        return billHistoryModels.size();
    }

    public class BillHistoryHolder extends RecyclerView.ViewHolder {

        ImageView BillHistory_Image;
        TextView BillHistory_Date, BillHistory_status;

        public BillHistoryHolder(@NonNull View itemView) {
            super(itemView);

            BillHistory_Date = itemView.findViewById(R.id.Bill_History_Date);
            BillHistory_status = itemView.findViewById(R.id.Bill_History_Status);
            BillHistory_Image = itemView.findViewById(R.id.Bill_History_image);
        }
    }
}
