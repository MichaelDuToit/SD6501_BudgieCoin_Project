package com.example.sd6501_budgiecoin_project;

import java.util.Date;

public class Transaction {
    // Fields for the Class
    private String name;
    private double value;
    private Account account;
    private String note;
    private Date date;

    // Constructors for the Class
    public Transaction(){
        this.name = null;
        this.value = 0.0;
        this.account = null;
        this.note = null;
        this.date  = new Date();
    }
    public Transaction(String name, double value, Date date, Account account, String note){
        this.name = name;
        this.value = value;
        this.account = account;
        this.note  = note;
        this.date = date;
    }

    // Accessor and Mutator Methods for the Class
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
    public void setAccount(Account account) {
        this.account = account;
    }
    public Account getAccount() {
        return account;
    }
    public void setNote(String note){this.note = note; }
    public String getNote(){return note; }
    public void setDate(Date date){this.date = date; }
    public Date getDate(){return date;}

    // Methods that manage transactions as Income (Positive) or Expenses (Negative)
    public double Expense(double val){
        val = -val;
        return this.value = val;
    }

    public double Income(double val){
        return this.value = val;
    }
}
