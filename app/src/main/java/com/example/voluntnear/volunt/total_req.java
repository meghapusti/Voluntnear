package com.example.voluntnear.volunt;

public class total_req {

    private String requestId;
    private String addr;
    private String date;
    private String reqtype;
    private String userId;
    private String time;
    private String desti_name;
    private String remarks;

    public total_req(String requestId, String addr, String date, String reqtype, String userId, String time, String desti_name, String remarks){
        this.requestId = requestId;
        this.addr = addr;
        this.date = date;
        this.reqtype = reqtype;
        this.userId = userId;
        this.time = time;
        this.desti_name = desti_name;
        this.remarks = remarks;
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

    public String getUserId(){return userId;}

    public String getTime() {return time;}

    public String getRemarks() {
        return remarks;
    }

    public String getDesti_name() {
        return desti_name;
    }
}
