package com.example.doc_pat.models;

import java.util.ArrayList;

public class Doctor {

    private String docId;
    private String fullName;
    private String address;
    private String phone_no;
    private String eduLevel;
    private String profession;
    private String specialization;
    private String rating;
    private String location;
    private String workPlace;
    private ArrayList<FeedBack> feedbackList;
    private ArrayList<Request> reqList;

//    constructors
    public Doctor() {
    }

    public Doctor(String docId, String fullName, String address, String phone_no, String eduLevel,
                  String profession, String specialization, String rating, String location, String workPlace,
                  ArrayList feedbackList, ArrayList reqList) {
        this.docId = docId;
        this.fullName = fullName;
        this.address = address;
        this.phone_no = phone_no;
        this.eduLevel = eduLevel;
        this.profession = profession;
        this.specialization = specialization;
        this.rating = rating;
        this.location = location;
        this.workPlace = workPlace;
        this.feedbackList = feedbackList;
        this.reqList = reqList;
    }

    public Doctor(String docId, String fullName, String address, String phone_no, String eduLevel,
                  String profession, String specialization, String rating, String location, String workPlace) {
        this.docId = docId;
        this.fullName = fullName;
        this.address = address;
        this.phone_no = phone_no;
        this.eduLevel = eduLevel;
        this.profession = profession;
        this.specialization = specialization;
        this.rating = rating;
        this.location = location;
        this.workPlace = workPlace;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
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

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public ArrayList<FeedBack> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(ArrayList<FeedBack> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public ArrayList<Request> getReqList() {
        return reqList;
    }

    public void setReqList(ArrayList<Request> reqList) {
        this.reqList = reqList;
    }
}
