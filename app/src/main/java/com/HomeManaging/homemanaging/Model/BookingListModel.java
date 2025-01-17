package com.HomeManaging.homemanaging.Model;

public class BookingListModel {


    private String userId;
    private String id;
    Room room;

    String total;


    private String name;
    String status;

    int bookingList_image;
    String bookingList_houseName, bookingList_location, startDate, endDate, bookingList_CustNmae, persons;

    public String getStatus() {
        return status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BookingListModel(int bookingList_image, String bookingList_houseName, String bookingList_location, String bookingList_StartDate, String bookingList_EndDate, String bookingList_CustNmae, String bookinhList_Persons) {
        this.bookingList_image = bookingList_image;
        this.bookingList_houseName = bookingList_houseName;
        this.bookingList_location = bookingList_location;
        this.startDate = bookingList_StartDate;
        this.endDate = bookingList_EndDate;
        this.bookingList_CustNmae = bookingList_CustNmae;
        this.persons = bookinhList_Persons;
    }

    public Room getRoom() {
        return room;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BookingListModel() {
    }

    public int getBookingList_image() {
        return bookingList_image;
    }

    public void setBookingList_image(int bookingList_image) {
        this.bookingList_image = bookingList_image;
    }

    public String getBookingList_houseName() {
        return bookingList_houseName;
    }

    public void setBookingList_houseName(String bookingList_houseName) {
        this.bookingList_houseName = bookingList_houseName;
    }

    public String getBookingList_location() {
        return bookingList_location;
    }

    public void setBookingList_location(String bookingList_location) {
        this.bookingList_location = bookingList_location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBookingList_CustNmae() {
        return bookingList_CustNmae;
    }

    public void setBookingList_CustNmae(String bookingList_CustNmae) {
        this.bookingList_CustNmae = bookingList_CustNmae;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }
}
