package com.HomeManaging.homemanaging.Model;

public class RentHouseModel {

    int House_image;
    String House_name, House_BRooms, House_MRooms,House_City;

    public RentHouseModel(int house_image, String house_name, String house_BRooms, String house_MRooms, String house_City) {
        House_image = house_image;
        House_name = house_name;
        House_BRooms = house_BRooms;
        House_MRooms = house_MRooms;
        House_City = house_City;
    }

    public int getHouse_image() {
        return House_image;
    }

    public void setHouse_image(int house_image) {
        House_image = house_image;
    }

    public String getHouse_name() {
        return House_name;
    }

    public void setHouse_name(String house_name) {
        House_name = house_name;
    }

    public String getHouse_BRooms() {
        return House_BRooms;
    }

    public void setHouse_BRooms(String house_BRooms) {
        House_BRooms = house_BRooms;
    }

    public String getHouse_MRooms() {
        return House_MRooms;
    }

    public void setHouse_MRooms(String house_MRooms) {
        House_MRooms = house_MRooms;
    }

    public String getHouse_City() {
        return House_City;
    }

    public void setHouse_City(String house_City) {
        House_City = house_City;
    }
}
