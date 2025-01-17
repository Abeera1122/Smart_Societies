package com.HomeManaging.homemanaging.Model;

public class ComplaintStatusModel {


    String ComplaintStatus_Subject, ComplaintStatus_id, ComplaintStatus_status;

    public ComplaintStatusModel(String complaintStatus_Subject, String complaintStatus_id, String complaintStatus_status) {
        ComplaintStatus_Subject = complaintStatus_Subject;
        ComplaintStatus_id = complaintStatus_id;
        ComplaintStatus_status = complaintStatus_status;
    }

    public String getComplaintStatus_Subject() {
        return ComplaintStatus_Subject;
    }

    public void setComplaintStatus_Subject(String complaintStatus_Subject) {
        ComplaintStatus_Subject = complaintStatus_Subject;
    }

    public String getComplaintStatus_id() {
        return ComplaintStatus_id;
    }

    public void setComplaintStatus_id(String complaintStatus_id) {
        ComplaintStatus_id = complaintStatus_id;
    }

    public String getComplaintStatus_status() {
        return ComplaintStatus_status;
    }

    public void setComplaintStatus_status(String complaintStatus_status) {
        ComplaintStatus_status = complaintStatus_status;
    }
}
