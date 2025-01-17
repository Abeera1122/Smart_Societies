package com.HomeManaging.homemanaging.Model;

public class BookingHistoryModel {

    String History_HouseName, History_HouseLocation, History_Status;
    int History_HouseImage;

    public BookingHistoryModel(String history_HouseName, String history_HouseLocation, String history_Status, int history_HouseImage) {
        History_HouseName = history_HouseName;
        History_HouseLocation = history_HouseLocation;
        History_Status = history_Status;
        History_HouseImage = history_HouseImage;
    }

    public String getHistory_HouseName() {
        return History_HouseName;
    }

    public void setHistory_HouseName(String history_HouseName) {
        History_HouseName = history_HouseName;
    }

    public String getHistory_HouseLocation() {
        return History_HouseLocation;
    }

    public void setHistory_HouseLocation(String history_HouseLocation) {
        History_HouseLocation = history_HouseLocation;
    }

    public String getHistory_Status() {
        return History_Status;
    }

    public void setHistory_Status(String history_Status) {
        History_Status = history_Status;
    }

    public int getHistory_HouseImage() {
        return History_HouseImage;
    }

    public void setHistory_HouseImage(int history_HouseImage) {
        History_HouseImage = history_HouseImage;
    }
}
