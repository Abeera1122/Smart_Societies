package com.HomeManaging.homemanaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HomeManaging.homemanaging.Model.EventsModel;
import com.HomeManaging.homemanaging.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    Context context;
    List<EventsModel> eventsModels;

    public EventsAdapter(Context context, List<EventsModel> eventsModels) {
        this.context = context;
        this.eventsModels = eventsModels;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_design, parent, false);
        EventViewHolder eventViewHolder= new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        holder.Event_Name.setText(eventsModels.get(position).getEvent_Name());
        holder.Event_Location.setText(eventsModels.get(position).getEvent_Location());
        holder.Event_Date.setText(eventsModels.get(position).getEvent_Date());
        holder.Event_Time.setText(eventsModels.get(position).getEvent_Time());
        holder.Event_Image.setImageResource(eventsModels.get(position).getEvent_Image());

    }

    @Override
    public int getItemCount() {
        return eventsModels.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        ImageView Event_Image;
        TextView Event_Name, Event_Location, Event_Date, Event_Time;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            Event_Image = itemView.findViewById(R.id.event_image);
            Event_Name = itemView.findViewById(R.id.event_name);
            Event_Date = itemView.findViewById(R.id.Event_date);
            Event_Location = itemView.findViewById(R.id.Event_location);
            Event_Time = itemView.findViewById(R.id.Event_time);
        }
    }
}
