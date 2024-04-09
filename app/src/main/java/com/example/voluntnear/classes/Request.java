package com.example.voluntnear.classes;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

public class Request {
    private String requestId;
    private String userId;
    private String requestType;

    private String date;
    private String time;
    private LatLng initLoc;
    private LatLng destLoc;


    // Default constructor (required by Firebase)
    public Request() {
        // Default constructor required for calls to DataSnapshot.getValue(Request.class)
    }

    public Request(String requestId, String userId, String requestType,String date,String time,LatLng initLoc,LatLng destLoc) {
        this.requestId = requestId;
        this.userId = userId;
        this.requestType = requestType;
        this.date=date;
        this.time=time;
        this.initLoc=initLoc;
        this.destLoc=destLoc;
    }

    public String getRequestId() {
        return requestId;
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

