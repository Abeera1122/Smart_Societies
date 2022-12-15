package com.HomeManaging.homemanaging.Model;

public class RentHouseModel {

    Integer house_image;
    String house_name, house_BRooms, house_MRooms,house_City;

    public RentHouseModel(int house_image, String house_name, String house_BRooms, String house_MRooms, String house_City) {
        this.house_image = house_image;
        this.house_name = house_name;
        this.house_BRooms = house_BRooms;
        this.house_MRooms = house_MRooms;
        this.house_City = house_City;
    }

    public int getHouse_image() {
        return house_image;
    }

    public void setHouse_image(int house_image) {
        this.house_image = house_image;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_BRooms() {
        return house_BRooms;
    }

    public void setHouse_BRooms(String house_BRooms) {
        this.house_BRooms = house_BRooms;
    }

    public String getHouse_MRooms() {
        return house_MRooms;
    }

    public void setHouse_MRooms(String house_MRooms) {
        this.house_MRooms = house_MRooms;
    }

    public String getHouse_City() {
        return house_City;
    }

    public void setHouse_City(String house_City) {
        this.house_City = house_City;
    }
}
