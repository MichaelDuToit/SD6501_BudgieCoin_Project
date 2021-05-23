package com.example.sd6501_budgiecoin_project;

public class User {
    // Attributes of the Class
    private int id;
    private String username;
    private String pinNumber;

    // Class Constructors
    public User(){
        this.id = 0;
        this.username = null;
        this.pinNumber = null;
    }

    public User(String name, String pin){
        this.id = 0;
        this.username = name;
        this.pinNumber = pin;
    }

    public User(int id, String name, String pin){
        this.id = id;
        this.username = name;
        this.pinNumber = pin;
    }

    // Accessor and Mutators of the Class
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPinNumber() {
        return pinNumber;
    }
    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
