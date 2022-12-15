package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.RentHouseModel;
import com.HomeManaging.homemanaging.R;

import java.util.List;

public class RentHouseAdapter extends RecyclerView.Adapter<RentHouseAdapter.RentHouseHolder> {

    Context context;
    List<RentHouseModel> rentHouseModelList;

    public RentHouseAdapter(Context context, List<RentHouseModel> rentHouseModelList) {
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

        holder.House_name.setText(rentHouseModelList.get(position).getHouse_name());
        holder.House_Image.setImageResource(rentHouseModelList.get(position).getHouse_image());
        holder.House_BRooms.setText(rentHouseModelList.get(position).getHouse_BRooms());
        holder.House_MRooms.setText(rentHouseModelList.get(position).getHouse_MRooms());
        holder.House_location.setText(rentHouseModelList.get(position).getHouse_City());
    }

    @Override
    public int getItemCount() {
        return rentHouseModelList.size();
    }

    public class RentHouseHolder extends RecyclerView.ViewHolder{

        ImageView House_Image;
        TextView House_name, House_location, House_BRooms, House_MRooms;

        public RentHouseHolder(@NonNull View itemView) {
            super(itemView);

            House_Image = itemView.findViewById(R.id._rent_house_image);
            House_name = itemView.findViewById(R.id._rent_house_title);
            House_location = itemView.findViewById(R.id._location_city_text);
            House_BRooms = itemView.findViewById(R.id._no_of_bathrooms);
            House_MRooms = itemView.findViewById(R.id._no_of_masters);
        }
    }
}
