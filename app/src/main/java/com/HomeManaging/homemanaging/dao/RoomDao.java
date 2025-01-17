package com.HomeManaging.homemanaging.dao;

import androidx.annotation.NonNull;

import com.HomeManaging.homemanaging.Model.BookingListModel;
import com.HomeManaging.homemanaging.Model.Room;
import com.abidingtech.base.callback.DataCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    public static DatabaseReference rentDatabase = FirebaseDatabase.getInstance().getReference("RentRooms");
    public static DatabaseReference saleDatabase = FirebaseDatabase.getInstance().getReference("SaleRooms");
    public static DatabaseReference bookingDatabase = FirebaseDatabase.getInstance().getReference("Bookings");

    private static RoomDao _this;

    private RoomDao() {

    }

    public static RoomDao getInstance() {
        if (_this == null) {
            _this = new RoomDao();
        }
        return _this;
    }

    public void getBookingHistory(DataCallback<List<BookingListModel>> dataCallback) {
        bookingDatabase.orderByChild("userId").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<BookingListModel> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(BookingListModel.class));
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


    public void addBooking(BookingListModel bookingListModel, DataCallback<String> dataCallback) {
        String id = bookingDatabase.push().getKey();
        bookingListModel.setUserId(FirebaseAuth.getInstance().getUid());
        bookingListModel.setStatus("Pending");
        bookingListModel.setId(id);
        bookingDatabase.child(id).setValue(bookingListModel).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dataCallback.onData("Booked");
            } else {
                dataCallback.onError(task.getException().getLocalizedMessage() + "");
            }
        });
    }

    public void getRentRooms(DataCallback<List<Room>> roomCallback) {
        rentDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Room> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(Room.class));
                    }
                    roomCallback.onData(list);
                } else {
                    roomCallback.onError("Empty");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getSaleRooms(DataCallback<List<Room>> roomCallback) {
        saleDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Room> list = new ArrayList<>();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(Room.class));
                    }
                    roomCallback.onData(list);
                } else {
                    roomCallback.onError("Empty");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
