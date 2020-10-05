package com.example.doc_pat.models;

public class Request {

    private String fbId;
    private String docId;
    private String patId;
    private String reqId;
    private String date;

    public Request() {
    }

    public Request(String fbId, String docId, String patId, String reqId, String date) {
        this.fbId = fbId;
        this.docId = docId;
        this.patId = patId;
        this.reqId = reqId;
        this.date = date;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
