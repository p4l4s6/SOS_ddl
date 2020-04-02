package com.gh0stcr4ck3r.besafe.models;

/**
 * @author : Raisul Islam
 * @date : 10-Mar-2020 1:49 AM
 * -:=Think=:--:=Write=:--:=Innovate=:-
 * Copyright (C) 2020 - All Rights Reserved
 **/
public class Contact {
    private String uid;
    private String name;
    private String phone;
    private String email;
    private String address;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
