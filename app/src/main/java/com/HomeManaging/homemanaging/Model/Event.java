package com.HomeManaging.homemanaging.Model;

public class Event {
    private String id;
    private String imageUrl, city, startDate, name, time;

    public Event(String imageUrl, String city, String startDate, String time, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.city = city;
        this.startDate = startDate;
        this.name = name;
        this.time = time;
    }

    public Event() {
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
