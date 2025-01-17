package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.Bill;
import com.HomeManaging.homemanaging.Model.Complaint;
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

public class ComplaintDao {
    public static DatabaseReference poolDatabase = FirebaseDatabase.getInstance().getReference("Complaints");
//    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");

    private static ComplaintDao _this;

    private ComplaintDao() {
    }

    public static ComplaintDao getInstance() {
        if (_this == null) {
            _this = new ComplaintDao();
        }
        return _this;
    }

    public void add(Complaint complaint, DataCallback<String> dataCallback) {
        complaint.setUserId(UserDao.getInstance().getUserId());
        String id = poolDatabase.push().getKey();
        complaint.setId(id);
        complaint.setStatus("Pending");
        poolDatabase.child(id).setValue(complaint).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dataCallback.onData("Added Successfully");

            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }

    public void getComplaints(DataCallback<List<Complaint>> dataCallback) {
        String userId = UserDao.getInstance().getUserId();
        poolDatabase.orderByChild("userId").equalTo(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            List<Complaint> list = new ArrayList<>();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                list.add(snapshot1.getValue(Complaint.class));
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
