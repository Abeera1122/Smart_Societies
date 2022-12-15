package com.HomeManaging.homemanaging.Model;

public class BillModel {

    int Bill_image;
    String Bill_date;

    public BillModel(int bill_image, String bill_date) {
        Bill_image = bill_image;
        Bill_date = bill_date;
    }

    public int getBill_image() {
        return Bill_image;
    }

    public void setBill_image(int bill_image) {
        Bill_image = bill_image;
    }

    public String getBill_date() {
        return Bill_date;
    }

    public void setBill_date(String bill_date) {
        Bill_date = bill_date;
    }
}
