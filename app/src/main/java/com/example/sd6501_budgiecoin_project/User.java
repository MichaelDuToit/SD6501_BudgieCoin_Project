package com.example.sd6501_budgiecoin_project;

public class User {
    // Attributes of the Class
    private String username;
    private String pinNumber;

    // Class Constructors
    public User(String name, String pin){
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


}
