package com.HomeManaging.homemanaging;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.HomeManaging.homemanaging.Adapter.BillAdapter;
import com.HomeManaging.homemanaging.Adapter.EventsAdapter;
import com.HomeManaging.homemanaging.Adapter.RentHouseAdapter;
import com.HomeManaging.homemanaging.Adapter.SaleHouseAdapter;
import com.HomeManaging.homemanaging.Model.Bill;
import com.HomeManaging.homemanaging.Model.BillModel;
import com.HomeManaging.homemanaging.Model.Event;
import com.HomeManaging.homemanaging.Model.EventsModel;
import com.HomeManaging.homemanaging.Model.RentHouseModel;
import com.HomeManaging.homemanaging.Model.Room;
import com.HomeManaging.homemanaging.Model.SaleHouseModel;
import com.HomeManaging.homemanaging.dao.BillDao;
import com.HomeManaging.homemanaging.dao.EventDao;
import com.HomeManaging.homemanaging.dao.RoomDao;
import com.abidingtech.base.callback.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView RentHouseRecyclerView, EventsRecyclerView, BillRecyclerView, SaleHouseRecyclerView;
    private List<RentHouseModel> rentHouseModelList;
    private List<EventsModel> eventsModelList1;
    private List<BillModel> billModelList;
    private List<SaleHouseModel> houseModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rentHouseModelList = new ArrayList<>();
        rentHouseModelList.add(new RentHouseModel(R.drawable.house_new_img,"Farm House","2","5","Sialkot"));
        rentHouseModelList.add(new RentHouseModel(R.drawable.house_new_img,"Farm House","2","5","Sialkot"));


        eventsModelList1 = new ArrayList<>();
        eventsModelList1.add(new EventsModel(R.drawable.house_new_img,"Qawali","Sialkot","4/10/2022","6:00 pm"));
        eventsModelList1.add(new EventsModel(R.drawable.house_new_img,"Qawali","Sialkot","4/10/2022","6:00 pm"));

        billModelList = new ArrayList<>();
        billModelList.add(new BillModel(R.drawable.house_new_img,"10/10/2022"));
        billModelList.add(new BillModel(R.drawable.house_new_img,"10/10/2022"));

        houseModels = new ArrayList<>();
        houseModels.add(new SaleHouseModel(R.drawable.house_new_img,"Villa House","2","5","Gujrat"));
        houseModels.add(new SaleHouseModel(R.drawable.house_new_img,"Villa House","2","5","Gujrat"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        //Rent House Recycler and Adapter

        //Event Recycler and Adapter



//        //Sale House Recycler and Adapter
//        SaleHouseRecyclerView = v.findViewById(R.id.Sale_recycler);
//        SaleHouseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        SaleHouseAdapter saleHouseAdapter = new SaleHouseAdapter(getContext(),houseModels);
//        SaleHouseRecyclerView.setAdapter(saleHouseAdapter);


        EventDao.getInstance().getEvents(new DataCallback<List<Event>>() {
            @Override
            public void onData(List<Event> data) {
                EventsRecyclerView = v.findViewById(R.id.event_recycler);
                EventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                EventsAdapter eventsAdapter = new EventsAdapter(getContext(),data);
                EventsRecyclerView.setAdapter(eventsAdapter);

            }

            @Override
            public void onError(String error) {

            }
        });

        RoomDao.getInstance().getRentRooms(new DataCallback<List<Room>>() {
            @Override
            public void onData(List<Room> data) {
                RentHouseRecyclerView = v.findViewById(R.id.rent_recycler);
                RentHouseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                RentHouseAdapter rentHouseAdapter = new RentHouseAdapter(getContext(),data);
                RentHouseRecyclerView.setAdapter(rentHouseAdapter);

            }

            @Override
            public void onError(String error) {

            }
        });
        RoomDao.getInstance().getSaleRooms(new DataCallback<List<Room>>() {
            @Override
            public void onData(List<Room> data) {
//                RentHouseRecyclerView = v.findViewById(R.id.rent_recycler);
//                RentHouseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//                RentHouseAdapter rentHouseAdapter = new RentHouseAdapter(getContext(),data);
//                RentHouseRecyclerView.setAdapter(rentHouseAdapter);
                //Sale House Recycler and Adapter
                SaleHouseRecyclerView = v.findViewById(R.id.Sale_recycler);
                SaleHouseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                SaleHouseAdapter saleHouseAdapter = new SaleHouseAdapter(getContext(),data);
                SaleHouseRecyclerView.setAdapter(saleHouseAdapter);
            }

            @Override
            public void onError(String error) {

            }
        });

        BillDao.getInstance().getBills(new DataCallback<List<Bill>>() {
            @Override
            public void onData(List<Bill> data) {
                //Bills Recycler and Adapter
                BillRecyclerView = v.findViewById(R.id.Bill_Recycler);
                BillRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                BillAdapter billAdapter = new BillAdapter(getContext(),data);
                BillRecyclerView.setAdapter(billAdapter);
            }

            @Override
            public void onError(String error) {

            }
        });
        return v;
    }

}