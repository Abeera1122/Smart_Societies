package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.HomeManaging.homemanaging.Model.Event;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class EventDetailActivity extends AppCompatActivity {

    Event event;

    ImageView imageView;
    TextView nameTxt, cityTxt, dateTxt, timeTxt;

    public static void loadActivity(Context context, Event event) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra("data", new Gson().toJson(event));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        event = new Gson().fromJson(getIntent().getStringExtra("data"), Event.class);
        imageView = findViewById(R.id.event_detail_image);
        nameTxt = findViewById(R.id.event_detail_name);
        cityTxt = findViewById(R.id.Event_detail_location);
        dateTxt = findViewById(R.id.Event_detail_date);
        timeTxt = findViewById(R.id.Event_detail_time);

        Glide.with(this).load(event.getImageUrl()).into(imageView);


        nameTxt.setText(event.getName());
        cityTxt.setText(event.getCity());
        dateTxt.setText(event.getStartDate());
        timeTxt.setText(event.getTime());
    }
}