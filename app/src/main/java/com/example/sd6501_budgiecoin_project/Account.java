package com.example.sd6501_budgiecoin_project;

public class Account {
    // Fields of the Class
    private int id;
    private String name;
    private double balance;

    // Constructors for the Class
    // Default Constructor
    public Account(){
        this.id = 0;
        this.name = null;
        this.balance = 0.0;
    }

    // Constructor that only takes name field. Primary use: creation of Account obj that gets
    // passed to DB (DB sets the ID).
    public Account(String name){
        this.id = 0;
        this.name = name;
        this.balance = 0.0;
    }

    // Constructor that takes all fields. Primary use: hold data from DB.
    public Account(int id, String name, double balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Accessor and Mutators for the Fields
    public int getId(){ return this.id; }
    public String getName(){
        return this.name;
    }
    public Double getBalance(){ return this.balance; }
    public void setId(int i){ this.id = i;}
    public void setName(String n){
        this.name = n;
    }
    public void setBalance(double b){ this.balance = b; }

    // Methods for the Class
}
