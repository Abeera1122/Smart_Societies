package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Adapter.ComplaintStatusAdapter;
import com.HomeManaging.homemanaging.Model.Complaint;
import com.HomeManaging.homemanaging.Model.ComplaintStatusModel;
import com.HomeManaging.homemanaging.dao.ComplaintDao;
import com.abidingtech.base.callback.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class ComplaintStatusActivity extends AppCompatActivity {

    RecyclerView ComplaintStatus_Recycler;
    private List<ComplaintStatusModel> complaintStatusModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_status);
//
//        complaintStatusModelList = new ArrayList<>();
//        complaintStatusModelList.add(new ComplaintStatusModel("Bill","2","Pending"));
//        complaintStatusModelList.add(new ComplaintStatusModel("Rent House","11","Solved"));
//

        ComplaintDao.getInstance().getComplaints(new DataCallback<List<Complaint>>() {
            @Override
            public void onData(List<Complaint> data) {
                ComplaintStatus_Recycler = findViewById(R.id.Complaint_Status_Recycler);
                ComplaintStatus_Recycler.setLayoutManager(new LinearLayoutManager(ComplaintStatusActivity.this, LinearLayoutManager.VERTICAL, false));
                ComplaintStatusAdapter complaintStatusAdapter = new ComplaintStatusAdapter(ComplaintStatusActivity.this, data);
                ComplaintStatus_Recycler.setAdapter(complaintStatusAdapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ComplaintStatusActivity.this, error + "", Toast.LENGTH_SHORT).show();

            }
        });

    }
}