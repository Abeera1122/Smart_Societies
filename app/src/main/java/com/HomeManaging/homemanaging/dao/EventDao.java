package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.Event;
import com.abidingtech.base.callback.DataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.homemanaging.homemanagingadmin.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDao {
    public static DatabaseReference poolDatabase = FirebaseDatabase.getInstance().getReference("Events");
//    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");

    private static EventDao _this;

    private EventDao() {
    }

    public static EventDao getInstance() {
        if (_this == null) {
            _this = new EventDao();
        }
        return _this;
    }

    public void addEvent(Event notice, DataCallback<String> dataCallback) {
        String key = poolDatabase.push().getKey();
        notice.setId(key);
        poolDatabase.child(key).setValue(notice).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dataCallback.onData("Notice added successfully");
            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }

    public void getEvents(DataCallback<List<Event>> dataCallback) {

        poolDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Event> events = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        events.add(snapshot1.getValue(Event.class));
                    }
                    dataCallback.onData(events);
                } else {
                    dataCallback.onError("Empty");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dataCallback.onError(error.getMessage() + "");
            }
        });
//        String key = poolDatabase.push().getKey();
//        notice.setId(key);
//        poolDatabase.child(key).setValue(notice).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                dataCallback.onData("Notice added successfully");
//            } else {
//                dataCallback.onError(task.getException().getLocalizedMessage() + "");
//            }
//        });
    }

}
