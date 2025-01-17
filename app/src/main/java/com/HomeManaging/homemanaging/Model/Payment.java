package com.HomeManaging.homemanaging.Model;

import com.abidingtech.base.model.User;

public class Payment {
    private String id;
    private String billId;
    private String userId;
    private String name;
    private String transactionId;
    private String paymentDate;
    private String imageUrl;
    private String status;

//    private Bill bill;
//    private User user;


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Payment(String id, String billId, String userId, String name, String transactionId, String paymentDate) {
        this.id = id;
        this.billId = billId;
        this.userId = userId;
        this.name = name;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
    }

    public Payment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
