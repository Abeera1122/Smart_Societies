package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.Complaint;
import com.HomeManaging.homemanaging.Model.ComplaintStatusModel;
import com.HomeManaging.homemanaging.R;

import java.util.List;


public class ComplaintStatusAdapter extends RecyclerView.Adapter<ComplaintStatusAdapter.ComplaintStatusHolder> {

    Context context;
    private List<Complaint> complaintStatusModels;

    public ComplaintStatusAdapter(Context context, List<Complaint> complaintStatusModels) {
        this.context = context;
        this.complaintStatusModels = complaintStatusModels;
    }

    @NonNull
    @Override
    public ComplaintStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.complaint_status_design, parent, false);
        ComplaintStatusHolder complaintStatusHolder = new ComplaintStatusHolder(view);
        return complaintStatusHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintStatusHolder holder, int position) {

        holder.ComplaintStatus_Subject.setText(complaintStatusModels.get(position).getSubject());
        holder.ComplaintStatus_id.setText(complaintStatusModels.get(position).getId());
        holder.ComplaintStatus_status.setText(complaintStatusModels.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return complaintStatusModels.size();
    }

    public class ComplaintStatusHolder extends RecyclerView.ViewHolder{

        TextView ComplaintStatus_Subject, ComplaintStatus_id, ComplaintStatus_status;

        public ComplaintStatusHolder(@NonNull View itemView) {
            super(itemView);

            ComplaintStatus_Subject = itemView.findViewById(R.id.Complaint_Status_subject);
            ComplaintStatus_id = itemView.findViewById(R.id.complaint_Status_no);
            ComplaintStatus_status = itemView.findViewById(R.id.Complaint_Status);
        }
    }

}
