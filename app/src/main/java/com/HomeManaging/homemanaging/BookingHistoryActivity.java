package com.HomeManaging.homemanaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.HomeManaging.homemanaging.Adapter.BookingHistoryAdapter;
import com.HomeManaging.homemanaging.Model.BookingHistoryModel;
import com.HomeManaging.homemanaging.Model.BookingListModel;
import com.HomeManaging.homemanaging.dao.RoomDao;
import com.abidingtech.base.BaseActivity;
import com.abidingtech.base.callback.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends BaseActivity {

    RecyclerView Booking_History_RecyclerView;
    private List<BookingHistoryModel> bookingHistoryModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        showLoading();
        RoomDao.getInstance().getBookingHistory(new DataCallback<List<BookingListModel>>() {
            @Override
            public void onData(List<BookingListModel> data) {
                dismiss();

//                bookingHistoryModelList = new ArrayList<>();
//                bookingHistoryModelList.add(new BookingHistoryModel("Farm House","Lahore","Approved",R.drawable.house_new_img));
//                bookingHistoryModelList.add(new BookingHistoryModel("Villa House","Lahore","Rejected",R.drawable.house_new_img));
//                bookingHistoryModelList.add(new BookingHistoryModel("Resort House","Lahore","Pending",R.drawable.house_new_img));


                Booking_History_RecyclerView = findViewById(R.id.Booking_history_Recycler);
                Booking_History_RecyclerView.setLayoutManager(new LinearLayoutManager(BookingHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                BookingHistoryAdapter bookingHistoryAdapter = new BookingHistoryAdapter(BookingHistoryActivity.this, data);
                Booking_History_RecyclerView.setAdapter(bookingHistoryAdapter);
            }

            @Override
            public void onError(String error) {
                dismiss();
                Toast.makeText(BookingHistoryActivity.this, error + "", Toast.LENGTH_SHORT).show();
            }
        });


    }
}