package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.HomeManaging.homemanaging.Model.Room;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class RentHouseDetailsActivity extends AppCompatActivity {

    Button RentHouseBook;

    Room room;

    ImageView imageView;
    boolean isForSale;

    TextView nameTxt, cityTxt, addressTxt, bathRooms, masterRooms, sellerNameTxt, phoneTxt;

    public static void loadActivity(Context context, Room room, boolean isForSale) {
        Intent intent = new Intent(context, RentHouseDetailsActivity.class);
        intent.putExtra("data", new Gson().toJson(room));
        intent.putExtra("isForSale", isForSale);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_house_details);

        isForSale = getIntent().getBooleanExtra("isForSale", false);
        room = new Gson().fromJson(getIntent().getStringExtra("data"), Room.class);
        imageView = findViewById(R.id.RentHouseDetailImage);
        nameTxt = findViewById(R.id.RentHouseDetailName);
        cityTxt = findViewById(R.id.RentHouseDetailCity);
        addressTxt = findViewById(R.id.RentHouseDetailAddress);
        bathRooms = findViewById(R.id._no_of_bathrooms);
        masterRooms = findViewById(R.id._no_of_masters);
        sellerNameTxt = findViewById(R.id.RentHouseDetailSellerName);
        phoneTxt = findViewById(R.id.RentHouseDetailSellerPhone);

        Glide.with(this).load(room.getImageUrl()).into(imageView);

        nameTxt.setText(room.getName());
        cityTxt.setText(room.getCity());
        addressTxt.setText(room.getAddress());
        bathRooms.setText(room.getBathroom());
        masterRooms.setText(room.getMaterRooms());
        sellerNameTxt.setText(room.getOwnerName());
        phoneTxt.setText(room.getOwnerNumber());


        RentHouseBook = findViewById(R.id.RentHouseBookNow);
        if (!isForSale) {
            RentHouseBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookingActivity.loadActivity(RentHouseDetailsActivity.this, room);
                }
            });
        }

    }
}