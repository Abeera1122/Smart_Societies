package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.HomeManaging.homemanaging.Model.Bill;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class BillDetailsActivity extends AppCompatActivity {

    Bill bill;
    Button PayNowBtn;
    ImageView imageView;
    TextView textView;

    public static void loadActivity(Context context, Bill bill) {
        Intent intent = new Intent(context, BillDetailsActivity.class);
        intent.putExtra("data", new Gson().toJson(bill));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        bill = new Gson().fromJson(getIntent().getStringExtra("data"), Bill.class);

        imageView = findViewById(R.id.Bill_Detail_image);
        textView = findViewById(R.id.Bill_Detail_Date);

        textView.setText(bill.getDeadLine());


        Glide.with(this).load(bill.getImageUrl()).into(imageView);


        PayNowBtn = findViewById(R.id.BillPayBtn);
        PayNowBtn.setOnClickListener(view -> {
            BillPaymentActivity.loadActivity(this, bill);
//            startActivity(new Intent(BillDetailsActivity.this, BillPaymentActivity.class));
        });
    }
}