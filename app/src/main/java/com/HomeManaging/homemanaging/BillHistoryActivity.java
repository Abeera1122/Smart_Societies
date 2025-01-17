package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Adapter.BillHistoryAdapter;
import com.HomeManaging.homemanaging.Model.Bill;
import com.HomeManaging.homemanaging.Model.BillHistoryModel;
import com.HomeManaging.homemanaging.Model.Payment;
import com.HomeManaging.homemanaging.dao.PaymentDao;
import com.abidingtech.base.callback.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class BillHistoryActivity extends AppCompatActivity {

    RecyclerView BillHistory_Recycler;
    private List<BillHistoryModel> billHistoryModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_history);

//        PaymentDao.getInstance().getBills(new DataCallback<List<Payment>>() {
//            @Override
//            public void onData(List<Payment> data) {
//
//                BillHistory_Recycler = findViewById(R.id.Bill_history_Recycler);
//                BillHistory_Recycler.setLayoutManager(new LinearLayoutManager(BillHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
//                BillHistoryAdapter billHistoryAdapter = new BillHistoryAdapter(BillHistoryActivity.this, data);
//                BillHistory_Recycler.setAdapter(billHistoryAdapter);
//            }
//
//            @Override
//            public void onError(String error) {
//                Toast.makeText(BillHistoryActivity.this, error + "", Toast.LENGTH_SHORT).show();
//            }
//        });
        billHistoryModelList = new ArrayList<>();
        billHistoryModelList.add(new BillHistoryModel("10/12/2022","Paid",R.drawable.house_new_img));
        billHistoryModelList.add(new BillHistoryModel("10/1/2021","Pending",R.drawable.house_new_img));



    }
}