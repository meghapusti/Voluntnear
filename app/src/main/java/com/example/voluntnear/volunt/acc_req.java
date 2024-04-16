package com.example.voluntnear.volunt;

public class acc_req {
    private String requestId;
    private String addr;
    private String date;
    private String reqtype;


    public acc_req(String requestId, String addr, String date, String reqtype) {
        this.requestId = requestId;
        this.addr = addr;
        this.date = date;
        this.reqtype = reqtype;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getAddr() {
        return addr;
    }

    public String getDate() {
        return date;
    }

    public String getReqtype() {
        return reqtype;
    }
}


