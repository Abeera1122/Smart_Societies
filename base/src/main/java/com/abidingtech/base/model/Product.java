package com.abidingtech.base.model;

public class Product {

    String name, categoryId, id;
    double price;
    String image, color, size;
    int stockQty, qty;

    public int orderQty;


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;

    }

    String decsription;


    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public String getDecsription() {
        return decsription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //    String picture1;

    public Product() {
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public Product(String name, double price, double discountPrice, String category) {
        this.name = name;
        this.price = price;

        this.categoryId = category;
    }

    public String getCategory() {
        return categoryId;
    }

    public void setCategory(String category) {
        this.categoryId = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIdValid() {
        return id != null && !id.isEmpty();
    }
    //    public String getPicture() {
//        return picture1;
//    }
//
//    public void setPicture(String picture) {
//        this.picture1 = picture;
//    }
}
