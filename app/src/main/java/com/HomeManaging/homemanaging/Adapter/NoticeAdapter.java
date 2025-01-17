package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.Notice;
import com.HomeManaging.homemanaging.Model.NoticeModel;
import com.HomeManaging.homemanaging.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    Context context;
    List<Notice> noticeModelList;

    public NoticeAdapter(Context context, List<Notice> noticeModelList) {
        this.context = context;
        this.noticeModelList = noticeModelList;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_design,parent,false);
        NoticeViewHolder noticeViewHolder = new NoticeViewHolder(view);
        return noticeViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {

//        holder.NoticeImage.setImageResource(noticeModelList.get(position).getImageUrl());
        Glide.with(context).load(noticeModelList.get(position).getImageUrl()).into( holder.NoticeImage);


    }

    @Override
    public int getItemCount() {
        return noticeModelList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder{

        ImageView NoticeImage;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            NoticeImage = itemView.findViewById(R.id.Notice_Image);
        }
    }
}
