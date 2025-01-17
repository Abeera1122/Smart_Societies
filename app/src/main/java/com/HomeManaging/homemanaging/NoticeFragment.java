package com.HomeManaging.homemanaging;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.HomeManaging.homemanaging.Adapter.NoticeAdapter;
import com.HomeManaging.homemanaging.Model.Notice;
import com.HomeManaging.homemanaging.Model.NoticeModel;
import com.HomeManaging.homemanaging.dao.NoticeDao;
import com.abidingtech.base.callback.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends Fragment {

    RecyclerView Notice_RecyclerView;
//    List<NoticeModel> noticeModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        noticeModels = new ArrayList<>();
//        noticeModels.add(new NoticeModel(R.drawable.house_new_img));
//        noticeModels.add(new NoticeModel(R.drawable.house_new_img));
//        noticeModels.add(new NoticeModel(R.drawable.house_new_img));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notice, container, false);

        NoticeDao.getInstance().getNotices(new DataCallback<List<Notice>>() {
            @Override
            public void onData(List<Notice> data) {
                Notice_RecyclerView = v.findViewById(R.id.Notice_Recycler);
                Notice_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                NoticeAdapter noticeAdapter = new NoticeAdapter(getContext(),data);
                Notice_RecyclerView.setAdapter(noticeAdapter);
            }

            @Override
            public void onError(String error) {

            }
        });

        return v;
    }
}