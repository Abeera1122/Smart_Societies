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

import com.HomeManaging.homemanaging.Model.RentHouseModel;
import com.HomeManaging.homemanaging.Model.Room;
import com.HomeManaging.homemanaging.Model.SaleHouseModel;
import com.HomeManaging.homemanaging.R;
import com.HomeManaging.homemanaging.SaleHouseDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class SaleHouseAdapter extends RecyclerView.Adapter<SaleHouseAdapter.SaleViewHolder> {

    Context context;
    List<Room> saleHouseModels;

    public SaleHouseAdapter(Context context, List<Room> saleHouseModels) {
        this.context = context;
        this.saleHouseModels = saleHouseModels;
    }

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sale_house_design, parent, false);
        SaleViewHolder saleViewHolder = new SaleViewHolder(view);
        return saleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHolder holder, int position) {

        holder.Sale_House_name.setText(saleHouseModels.get(position).getName());
//        holder.Sale_House_Image.setImageResource(saleHouseModels.get(position).getSale_house_image());
        holder.Sale_House_BRooms.setText(saleHouseModels.get(position).getBathroom());
        holder.Sale_House_MRooms.setText(saleHouseModels.get(position).getMaterRooms());
        holder.Sale_House_location.setText(saleHouseModels.get(position).getCity());


        Glide.with(context).load(saleHouseModels.get(position).getImageUrl()).into(holder.Sale_House_Image);

        holder.SaleHouseCard.setOnClickListener(view -> {
            SaleHouseDetailActivity.loadActivity(context, saleHouseModels.get(position));
//            context.startActivity(new Intent(context, SaleHouseDetailActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return saleHouseModels.size();
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder {

        ImageView Sale_House_Image;
        TextView Sale_House_name, Sale_House_location, Sale_House_BRooms, Sale_House_MRooms;
        CardView SaleHouseCard;

        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);

            Sale_House_Image = itemView.findViewById(R.id.sale_house_image);
            Sale_House_name = itemView.findViewById(R.id.sale_house_title);
            Sale_House_location = itemView.findViewById(R.id.sale_location_city_text);
            Sale_House_BRooms = itemView.findViewById(R.id.Sale_no_of_bathrooms);
            Sale_House_MRooms = itemView.findViewById(R.id.sale_no_of_masters);
            SaleHouseCard = itemView.findViewById(R.id.Sale_house_cardView);

        }
    }
}
