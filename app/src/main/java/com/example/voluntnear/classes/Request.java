package com.example.voluntnear.classes;

import com.google.firebase.database.Exclude;

public class Request {
    private String requestId;
    private String userId;
    private String requestType;

    // Default constructor (required by Firebase)
    public Request() {
        // Default constructor required for calls to DataSnapshot.getValue(Request.class)
    }

    public Request(String requestId, String userId, String requestType) {
        this.requestId = requestId;
        this.userId = userId;
        this.requestType = requestType;
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

    // Exclude the requestId from being stored in the database (since it's already the key)
    @Exclude
    public String getKey() {
        return requestId;
    }
}

