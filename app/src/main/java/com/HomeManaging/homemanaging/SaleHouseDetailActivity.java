package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.HomeManaging.homemanaging.Model.Room;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class SaleHouseDetailActivity extends AppCompatActivity {
    ImageView imageView;
    boolean isForSale;

    TextView nameTxt, cityTxt, addressTxt, bathRooms, masterRooms, sellerNameTxt, phoneTxt;

    public static void loadActivity(Context context, Room room) {
        Intent intent = new Intent(context, SaleHouseDetailActivity.class);
        intent.putExtra("data", new Gson().toJson(room));
        context.startActivity(intent);
    }

    Room room;


    Button callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_house_detail);

        room = new Gson().fromJson(getIntent().getStringExtra("data"), Room.class);
        imageView = findViewById(R.id.SaleHouseDetailImage);
        nameTxt = findViewById(R.id.SaleHouseDetailName);
        cityTxt = findViewById(R.id.SaleHouseDetailCity);
        addressTxt = findViewById(R.id.SaleHouseDetailAddress);
        bathRooms = findViewById(R.id._no_of_bathrooms);
        masterRooms = findViewById(R.id._no_of_masters);
        sellerNameTxt = findViewById(R.id.SaleHouseDetailSellerName);
        phoneTxt = findViewById(R.id.SaleHouseDetailSellerPhone);
        callBtn = findViewById(R.id.CallButton);

        Glide.with(this).load(room.getImageUrl()).into(imageView);

        nameTxt.setText(room.getName());
        cityTxt.setText(room.getCity());
        addressTxt.setText(room.getAddress());
        bathRooms.setText(room.getBathroom());
        masterRooms.setText(room.getMaterRooms());

        sellerNameTxt.setText(room.getOwnerName());
        phoneTxt.setText(room.getOwnerNumber());

        callBtn.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;

                }
            }
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + room.getOwnerNumber()));
            startActivity(callIntent);
        });


    }
}