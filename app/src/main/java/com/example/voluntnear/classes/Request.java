package com.example.voluntnear.classes;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

public class Request {
    private String requestId;
    private String userId;
    private String requestType;

    private String init_name;
    private String desti_name;

    private String date;
    private String time;
    private LatLng initLoc;
    private LatLng destLoc;
    private String remarks;


    // Default constructor (required by Firebase)
    public Request() {
        // Default constructor required for calls to DataSnapshot.getValue(Request.class)
    }


    public Request(String reqID, String userId, String reqType, String date, String time, String init_name, String desti_name, LatLng initLoc, LatLng destLoc, String remarks) {
        this.requestId = reqID;
        this.userId = userId;
        this.requestType = reqType;
        this.date=date;
        this.time=time;
        this.initLoc=initLoc;
        this.init_name = init_name;
        this.desti_name= desti_name;
        this.destLoc=destLoc;
        this.remarks = remarks;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getInit_name() {
        return init_name;
    }

    public String getDesti_name() {
        return desti_name;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDate(){return date;}
    public void setDate(String date){this.date=date;}
    public String getTime(){return time;}
    public void setTime(String time){this.time=time;}
    public LatLng getInitLoc(){return initLoc;}
    public void setInitLoc(LatLng initLoc){this.initLoc=initLoc;}
    public LatLng getDestLoc(){return destLoc;}
    public void setDestLoc(LatLng destLoc){this.destLoc=destLoc;}



    // Exclude the requestId from being stored in the database (since it's already the key)
    @Exclude
    public String getKey() {
        return requestId;
    }
}

