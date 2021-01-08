package com.example.testproiect.logare;

public class User {
    private String username;
    private String nume;


    public User() {

    }
    public User(String username, String nume) {
        this.username = username;
        this.nume = nume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
