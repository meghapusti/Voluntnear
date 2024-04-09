package com.example.voluntnear.classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email, name, password, phno, role;
    private List<Request> accepted_list;
    private List<Request> pending_list;



    public User(String email, String name, String password, String phno, String role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phno = phno;
        this.role = role;
        this.accepted_list=new ArrayList<>();
        this.pending_list=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public List<Request> getAccepted_list(){return accepted_list;}
    public List<Request> getPending_list(){return pending_list;}

    public void add_accepted(Request new_req){
        accepted_list.add(new_req);
    }
    public void remove_accepted(Request req){accepted_list.remove(req);}
    public void add_pending(Request new_req){
        pending_list.add(new_req);

    }
    public void remove_pending(Request req){pending_list.remove(req);}

}
