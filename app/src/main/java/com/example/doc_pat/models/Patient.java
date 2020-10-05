package com.example.doc_pat.models;

import java.util.ArrayList;

public class Patient {

    private String patId;
    private String fullName;
    private String address;
    private String phone;
    private String disease;
    private ArrayList<Request> reqHistoryList;

//    constructors

    public Patient() {
    }

    public Patient(String patId, String fullName, String address, String phone, String disease, ArrayList reqHistoryList) {
        this.patId = patId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.disease = disease;
        this.reqHistoryList = reqHistoryList;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public ArrayList<Request> getReqHistoryList() {
        return reqHistoryList;
    }

    public void setReqHistoryList(ArrayList<Request> reqHistoryList) {
        this.reqHistoryList = reqHistoryList;
    }
}
