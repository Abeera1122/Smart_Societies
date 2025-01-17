package com.HomeManaging.homemanaging.Model;

public class Bill {
    private String imageUrl,deadLine,id;

    public Bill(String imageUrl, String deadLine) {
        this.imageUrl = imageUrl;
        this.deadLine = deadLine;
    }

    public Bill() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
