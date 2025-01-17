package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.Pool;
import com.abidingtech.base.callback.DataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PoolDao {
    public static DatabaseReference poolDatabase = FirebaseDatabase.getInstance().getReference("Pools");
//    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");

    private static PoolDao _this;

    private PoolDao() {
    }

    public static PoolDao getInstance() {
        if (_this == null) {
            _this = new PoolDao();
        }
        return _this;
    }

    public void addPool(Pool room, DataCallback<String> dataCallback) {


        poolDatabase.child(room.getId()).setValue(room).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dataCallback.onData("Pool added successfully");
            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }

    public void getPool(DataCallback<Pool> dataCallback) {
        poolDatabase.child("Pool").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    dataCallback.onData(snapshot.getValue(Pool.class));
                } else {
                    dataCallback.onError("Empty");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
