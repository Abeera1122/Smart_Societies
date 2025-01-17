package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.BookingHistoryModel;
import com.HomeManaging.homemanaging.Model.BookingListModel;
import com.HomeManaging.homemanaging.Model.RentHouseModel;
import com.HomeManaging.homemanaging.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryHolder>{

    Context context;
    List<BookingListModel> bookingHistoryModels;

    public BookingHistoryAdapter(Context context, List<BookingListModel> bookingHistoryModels) {
        this.context = context;
        this.bookingHistoryModels = bookingHistoryModels;
    }

    @NonNull
    @Override
    public BookingHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_history_design, parent, false);
        BookingHistoryHolder bookingHistoryHolder = new BookingHistoryHolder(view);
        return bookingHistoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryHolder holder, int position) {

        holder.History_houseName.setText(bookingHistoryModels.get(position).getRoom().getName());
        holder.History_houseLocation.setText(bookingHistoryModels.get(position).getRoom().getCity());
        holder.History_Status.setText(bookingHistoryModels.get(position).getStatus());
//        holder.History_HouseImage.setImageResource(bookingHistoryModels.get(position).getHistory_HouseImage());
        Glide.with(context).load(bookingHistoryModels.get(position).getRoom().getImageUrl()).into(holder.History_HouseImage);

    }

    @Override
    public int getItemCount() {
        return bookingHistoryModels.size();
    }

    public class BookingHistoryHolder extends RecyclerView.ViewHolder{

        TextView History_houseName, History_houseLocation, History_Status;
        ImageView History_HouseImage;

        public BookingHistoryHolder(@NonNull View itemView) {
            super(itemView);

            History_HouseImage = itemView.findViewById(R.id.Booking_History_house_image);
            History_houseName = itemView.findViewById(R.id.Booking_History_house_title);
            History_houseLocation = itemView.findViewById(R.id.Booking_History_location_city);
            History_Status = itemView.findViewById(R.id.Booking_History_House_Status);
        }
    }
}
