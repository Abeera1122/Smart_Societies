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
import com.HomeManaging.homemanaging.R;
import com.HomeManaging.homemanaging.RentHouseDetailsActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class RentHouseAdapter extends RecyclerView.Adapter<RentHouseAdapter.RentHouseHolder> {

    Context context;
    List<Room> rentHouseModelList;

    public RentHouseAdapter(Context context, List<Room> rentHouseModelList) {
        this.context = context;
        this.rentHouseModelList = rentHouseModelList;
    }

    @NonNull
    @Override
    public RentHouseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rent_house_design, parent, false);
        RentHouseHolder houseHolder = new RentHouseHolder(view);
        return houseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RentHouseHolder holder, int position) {

        holder.House_name.setText(rentHouseModelList.get(position).getName());
//        holder.House_Image.setImageResource(rentHouseModelList.get(position).getHouse_image());
        holder.House_BRooms.setText(rentHouseModelList.get(position).getBathroom());
        holder.House_MRooms.setText(rentHouseModelList.get(position).getMaterRooms());
        holder.House_location.setText(rentHouseModelList.get(position).getCity());

        Glide.with(context).load(rentHouseModelList.get(position).getImageUrl()).into(holder.House_Image);

        holder.RentHouseCard.setOnClickListener(view -> {
            RentHouseDetailsActivity.loadActivity(context, rentHouseModelList.get(position),false);
//            Intent intent = new Intent(context, RentHouseDetailsActivity.class);
//            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rentHouseModelList.size();
    }

    public class RentHouseHolder extends RecyclerView.ViewHolder {

        ImageView House_Image;
        TextView House_name, House_location, House_BRooms, House_MRooms;
        CardView RentHouseCard;

        public RentHouseHolder(@NonNull View itemView) {
            super(itemView);

            House_Image = itemView.findViewById(R.id._rent_house_image);
            House_name = itemView.findViewById(R.id._rent_house_title);
            House_location = itemView.findViewById(R.id._location_city_text);
            House_BRooms = itemView.findViewById(R.id._no_of_bathrooms);
            House_MRooms = itemView.findViewById(R.id._no_of_masters);
            RentHouseCard = itemView.findViewById(R.id.rent_house_cardView);
        }
    }
}
