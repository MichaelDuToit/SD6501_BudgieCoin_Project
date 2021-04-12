package com.example.sd6501_budgiecoin_project;

public class Account {
    // Fields of the Class
    private String name;

    // Constructors for the Class
    public Account(){
        this.name = null;
    }
    public Account(String name){
        this.name = name;
    }

    // Accessor and Mutators for the Fields
    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }

    // Methods for the Class
}
