package com.HomeManaging.homemanaging;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.HomeManaging.homemanaging.Adapter.RentHouseAdapter;
import com.HomeManaging.homemanaging.Model.RentHouseModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView RentHouseRecyclerView;
    private List<RentHouseModel> rentHouseModelList1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rentHouseModelList1 = new ArrayList<>();
        rentHouseModelList1.add(new RentHouseModel(R.drawable.wall1,"Farm House","2","5","Sialkot"));
        rentHouseModelList1.add(new RentHouseModel(R.drawable.wall1,"Farm House","2","5","Sialkot"));
        rentHouseModelList1.add(new RentHouseModel(R.drawable.wall1,"Farm House","2","5","Sialkot"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RentHouseRecyclerView = v.findViewById(R.id.rent_recycler);
        RentHouseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        RentHouseAdapter rentHouseAdapter = new RentHouseAdapter(getContext(),rentHouseModelList1);
        RentHouseRecyclerView.setAdapter(rentHouseAdapter);
        return v;
    }

}