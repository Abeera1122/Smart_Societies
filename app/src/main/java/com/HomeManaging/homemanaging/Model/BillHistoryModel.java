package com.HomeManaging.homemanaging.Model;

public class BillHistoryModel {

    String BillHistory_Date, BillHistory_Status;
    int BillHistory_Image;

    public BillHistoryModel(String billHistory_Date, String billHistory_Status, int billHistory_Image) {
        BillHistory_Date = billHistory_Date;
        BillHistory_Status = billHistory_Status;
        BillHistory_Image = billHistory_Image;
    }

    public String getBillHistory_Date() {
        return BillHistory_Date;
    }

    public void setBillHistory_Date(String billHistory_Date) {
        BillHistory_Date = billHistory_Date;
    }

    public String getBillHistory_Status() {
        return BillHistory_Status;
    }

    public void setBillHistory_Status(String billHistory_Status) {
        BillHistory_Status = billHistory_Status;
    }

    public int getBillHistory_Image() {
        return BillHistory_Image;
    }

    public void setBillHistory_Image(int billHistory_Image) {
        BillHistory_Image = billHistory_Image;
    }
}
