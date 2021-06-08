package com.example.sd6501_budgiecoin_project;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    // Fields for the Class
    private int id;
    private String name;
    private double value;
    private int account;
    private String note;
    private Calendar datetime;

    // Constructors for the Class
    // Default Constructor - no params.
    public Transaction(){
        this.id = 0;
        this.name = null;
        this.value = 0.0;
        this.account = 0;
        this.note = null;
        this.datetime = Calendar.getInstance();
    }
    // Constructor that takes all fields except ID. Primary use for creating new transactions (DB assigns ID value)
    public Transaction(String name, double value, Calendar datetime, int account, String note){
        this.id = 0;
        this.name = name;
        this.value = value;
        this.account = account;
        this.note  = note;
        this.datetime = datetime;
    }
    // Constructor that takes all fields. Primary use: holding transaction data from DB.
    public Transaction(int id, String name, double value, Calendar datetime, int account, String note){
        this.id = id;
        this.name = name;
        this.value = value;
        this.account = account;
        this.note  = note;
        this.datetime = datetime;
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

    public void setDatetime(Calendar datetime){
        this.datetime = datetime;
    }
    public Calendar getDateTime() {
        return datetime;
    }

    public long getDateTimeLong(){
        long temp = datetime.getTimeInMillis();
        return temp;
    }
    public void setDateTimeLong(long datetime){
        this.datetime = Calendar.getInstance();
        this.datetime.setTimeInMillis(datetime);
    }
    public void setDateTimeLong(String datetime){
        long temp = Long.parseLong(datetime);
        this.datetime = Calendar.getInstance();
        this.datetime.setTimeInMillis(temp);
    }

}
