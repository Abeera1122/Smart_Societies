package com.HomeManaging.homemanaging.Model;

public class EventsModel {

    int Event_Image;
    String Event_Name, Event_Location, Event_Date, Event_Time;

    public EventsModel(int event_Image, String event_Name, String event_Location, String event_Date, String event_Time) {
        Event_Image = event_Image;
        Event_Name = event_Name;
        Event_Location = event_Location;
        Event_Date = event_Date;
        Event_Time = event_Time;
    }

    public int getEvent_Image() {
        return Event_Image;
    }

    public void setEvent_Image(int event_Image) {
        Event_Image = event_Image;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }

    public String getEvent_Location() {
        return Event_Location;
    }

    public void setEvent_Location(String event_Location) {
        Event_Location = event_Location;
    }

    public String getEvent_Date() {
        return Event_Date;
    }

    public void setEvent_Date(String event_Date) {
        Event_Date = event_Date;
    }

    public String getEvent_Time() {
        return Event_Time;
    }

    public void setEvent_Time(String event_Time) {
        Event_Time = event_Time;
    }
}
