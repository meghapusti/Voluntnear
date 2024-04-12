package com.example.voluntnear.classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email, name, phno, role;



    public User(String email, String name, String phno, String role) {
        this.email = email;
        this.name = name;
        this.phno = phno;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }



}
