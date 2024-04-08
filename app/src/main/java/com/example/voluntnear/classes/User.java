package com.example.voluntnear.classes;

public class User {
    private String email, name, password, phno, role;


    public User(String email, String name, String password, String phno, String role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phno = phno;
        this.role = role;
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

}
