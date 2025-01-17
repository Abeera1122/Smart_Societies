package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.Bill;
import com.abidingtech.base.callback.DataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.homemanaging.homemanagingadmin.Model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillDao {
    public static DatabaseReference poolDatabase = FirebaseDatabase.getInstance().getReference("Bills");
//    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");

    private static BillDao _this;

    private BillDao() {
    }

    public static BillDao getInstance() {
        if (_this == null) {
            _this = new BillDao();
        }
        return _this;
    }

    public void getBills(DataCallback<List<Bill>> dataCallback) {
        poolDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Bill> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(Bill.class));
                    }
                    dataCallback.onData(list);

                } else {
                    dataCallback.onError("Empty");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dataCallback.onError(error.getMessage() + "");

            }
        });
    }

}
