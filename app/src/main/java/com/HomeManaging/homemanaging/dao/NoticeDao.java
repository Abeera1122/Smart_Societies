package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.Notice;
import com.abidingtech.base.callback.DataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticeDao {
    public static DatabaseReference poolDatabase = FirebaseDatabase.getInstance().getReference("Notices");
//    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");

    private static NoticeDao _this;

    private NoticeDao() {
    }

    public static NoticeDao getInstance() {
        if (_this == null) {
            _this = new NoticeDao();
        }
        return _this;
    }


    public void getNotices(DataCallback<List<Notice>> dataCallback) {
        poolDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Notice> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(Notice.class));
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
//    public void addNotice(Notice notice, DataCallback<String> dataCallback) {
//        String key = poolDatabase.push().getKey();
//        notice.setId(key);
//        poolDatabase.child(key).setValue(notice).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                dataCallback.onData("Notice added successfully");
//            } else {
//                dataCallback.onError(task.getException().getLocalizedMessage() + "");
//            }
//        });
//    }

}
