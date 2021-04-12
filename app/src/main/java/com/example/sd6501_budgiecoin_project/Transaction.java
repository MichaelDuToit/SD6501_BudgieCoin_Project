package com.example.sd6501_budgiecoin_project;

public class Transaction {
    // Fields for the Class
    private String name;
    private double value;
    private Account account;
    private String note;

    // Constructors for the Class
    public Transaction(){
        this.name = null;
        this.value = 0.0;
        this.account = null;
        this.note = null;
    }
    public Transaction(String name, double value, Account account, String note){
        this.name = name;
        this.value = value;
        this.account = account;
        this.note  = note;
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
}
