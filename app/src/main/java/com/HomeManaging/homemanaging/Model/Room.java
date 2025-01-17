package com.HomeManaging.homemanaging.Model;

public class Room {
    private String id;
    private String name, city, address, bathroom, materRooms, imageUrl;
    private String ownerName, ownerNumber;

    public Room(String name, String city, String address, String bathroom, String materRooms) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.bathroom = bathroom;
        this.materRooms = materRooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Room() {
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public Room(String name, String city, String address, String bathroom, String materRooms, String imageUrl) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.bathroom = bathroom;
        this.materRooms = materRooms;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getMaterRooms() {
        return materRooms;
    }

    public void setMaterRooms(String materRooms) {
        this.materRooms = materRooms;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

