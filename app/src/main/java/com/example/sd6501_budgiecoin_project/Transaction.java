package com.example.sd6501_budgiecoin_project;

import java.util.Date;

public class Transaction {
    // Fields for the Class
    private int id;
    private String name;
    private double value;
    private int account;
    private String note;
    private String date;
    private String time;

    // Constructors for the Class
    // Default Constructor - no params.
    public Transaction(){
        this.id = 0;
        this.name = null;
        this.value = 0.0;
        this.account = 0;
        this.note = null;
        this.date  = null;
        this.time = null;
    }
    // Constructor that takes all fields except ID. Primary use for creating new transactions (DB assigns ID value)
    public Transaction(String name, double value, String date, String time, int account, String note){
        this.id = 0;
        this.name = name;
        this.value = value;
        this.account = account;
        this.note  = note;
        this.date = date;
        this.time = time;
    }
    // Constructor that takes all fields. Primary use: holding transaction data from DB.
    public Transaction(int id, String name, double value, String date, String time, int account, String note){
        this.id = id;
        this.name = name;
        this.value = value;
        this.account = account;
        this.note  = note;
        this.date = date;
        this.time = time;
    }

    // Accessor and Mutator Methods for the Class
    public void setId(int id){ this.id = id; }
    public int getId(){ return this.id; }
    public void setName(String name) { this.name = name; }
    public String getName() {
        return name;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
    public void setAccount(int account) {
        this.account = account;
    }
    public int getAccount() {
        return account;
    }
    public void setNote(String note){this.note = note; }
    public String getNote(){return note; }
    public void setDate(String date){this.date = date; }
    public String getDate(){return date;}
    public void setTime(String time){this.time = time;}
    public String getTime(){return time; }

}
