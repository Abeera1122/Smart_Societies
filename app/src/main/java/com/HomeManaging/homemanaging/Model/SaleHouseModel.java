package com.HomeManaging.homemanaging.Model;

public class SaleHouseModel {

    Integer Sale_house_image;
    String sale_house_name, sale_house_BRooms, sale_house_MRooms, sale_house_City;

    public SaleHouseModel(Integer sale_house_image, String sale_house_name, String sale_house_BRooms, String sale_house_MRooms, String sale_house_City) {
        Sale_house_image = sale_house_image;
        this.sale_house_name = sale_house_name;
        this.sale_house_BRooms = sale_house_BRooms;
        this.sale_house_MRooms = sale_house_MRooms;
        this.sale_house_City = sale_house_City;
    }

    public Integer getSale_house_image() {
        return Sale_house_image;
    }

    public void setSale_house_image(Integer sale_house_image) {
        Sale_house_image = sale_house_image;
    }

    public String getSale_house_name() {
        return sale_house_name;
    }

    public void setSale_house_name(String sale_house_name) {
        this.sale_house_name = sale_house_name;
    }

    public String getSale_house_BRooms() {
        return sale_house_BRooms;
    }

    public void setSale_house_BRooms(String sale_house_BRooms) {
        this.sale_house_BRooms = sale_house_BRooms;
    }

    public String getSale_house_MRooms() {
        return sale_house_MRooms;
    }

    public void setSale_house_MRooms(String sale_house_MRooms) {
        this.sale_house_MRooms = sale_house_MRooms;
    }

    public String getSale_house_City() {
        return sale_house_City;
    }

    public void setSale_house_City(String sale_house_City) {
        this.sale_house_City = sale_house_City;
    }
}
