package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.Bill;
import com.HomeManaging.homemanaging.Model.Payment;
import com.abidingtech.base.callback.DataCallback;
import com.abidingtech.base.dao.UserDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    public static DatabaseReference poolDatabase = FirebaseDatabase.getInstance().getReference("Payments");
//    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");

    private static PaymentDao _this;

    private PaymentDao() {
    }

    public static PaymentDao getInstance() {
        if (_this == null) {
            _this = new PaymentDao();
        }
        return _this;
    }

    public void add(Payment payment, DataCallback<String> dataCallback) {
        String id = poolDatabase.push().getKey();
        payment.setId(id);
        payment.setUserId(UserDao.getInstance().getUserId());
        payment.setStatus("Pending");
        poolDatabase.child(id).setValue(payment).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dataCallback.onData("Added Successfully");
            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });

    }

    public void getBills(DataCallback<List<Payment>> dataCallback) {

        poolDatabase.orderByChild("userId").equalTo(UserDao.getInstance().getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Payment> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(Payment.class));
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
