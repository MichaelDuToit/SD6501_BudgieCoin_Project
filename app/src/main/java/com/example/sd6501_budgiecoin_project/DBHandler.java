package com.example.sd6501_budgiecoin_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class DBHandler  extends SQLiteOpenHelper {
    // Database Config
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "BudgieCoinDB";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_TRANSACTIONS = "transactions";

    // Schema for Accounts Table
    private static final String KEY_ACC_ID = "id";
    private static final String KEY_ACC_NAME = "accountName";
    private static final String KEY_ACC_BAL = "accountBalance";

    // Schema for Transactions Table
    private static final String KEY_TRANS_ID = "id";
    private static final String KEY_TRANS_NAME = "transactionName";
    private static final String KEY_TRANS_VALUE = "value";
    private static final String KEY_TRANS_ACC = "account";
    private static final String KEY_TRANS_NOTE = "note";
    private static final String KEY_TRANS_DATE = "date";
    private static final String KEY_TRANS_TIME = "time";


    public DBHandler(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Query for Accounts Table
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ACC_NAME + " TEXT, "
                + KEY_ACC_BAL + " DOUBLE "
                + ")";

        // Create Query for Transactions Table
        String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
                + KEY_TRANS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TRANS_NAME + " TEXT, "
                + KEY_TRANS_VALUE + " DOUBLE, "
                + KEY_TRANS_ACC + " INTEGER, "
                + KEY_TRANS_NOTE + " TEXT, "
                + KEY_TRANS_DATE + " DATE, "
                + KEY_TRANS_TIME + " TEXT, "
                + " FOREIGN KEY (" + KEY_TRANS_ACC + ") REFERENCES " + TABLE_ACCOUNTS + "(" + KEY_ACC_ID + ")"
                + ")";
        // Run the Create Table queries for Account and Transactions
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        onCreate(db);
    }

    // To create an transaction in the DB, pass a transaction class to the method.
    public void createTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRANS_NAME, transaction.getName());
        values.put(KEY_TRANS_VALUE, transaction.getValue());
        values.put(KEY_TRANS_DATE, transaction.getDate());
        values.put(KEY_TRANS_TIME, transaction.getTime());
        values.put(KEY_TRANS_ACC, String.valueOf(transaction.getAccount()));
        values.put(KEY_TRANS_NOTE, transaction.getNote());
        long newRowId = db.insert(TABLE_TRANSACTIONS, null, values);
        db.close();
    }

    // To get a specific transaction from the DB, pass the transaction's ID to the method and it
    // will return the first record that has that ID.
    public Transaction getTransaction(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Transaction transaction = new Transaction();
        String query = "SELECT id, transactionName, value, account, note, date, time FROM " + TABLE_TRANSACTIONS + " WHERE " + KEY_TRANS_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()){
            transaction.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_TRANS_ID))));
            transaction.setName(cursor.getString(cursor.getColumnIndex(KEY_TRANS_NAME)));
            transaction.setAccount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_TRANS_ACC))));
            transaction.setValue(Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_TRANS_VALUE))));
            transaction.setDate(cursor.getString(cursor.getColumnIndex(KEY_TRANS_DATE)));
            transaction.setTime(cursor.getString(cursor.getColumnIndex(KEY_TRANS_TIME)));
            transaction.setNote(cursor.getString(cursor.getColumnIndex(KEY_TRANS_NOTE)));
            cursor.close();
        }
        return transaction;
    }

    // Get all the transactions in the Transaction table from the DB and return it in an ArrayList.
    public ArrayList<Transaction> getAllTransactions(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        String query = "SELECT id, transactionName, value, account, note, date, time FROM " + TABLE_TRANSACTIONS + " ORDER BY date desc, time desc";
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            Transaction transaction = new Transaction();
            transaction.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_TRANS_ID))));
            transaction.setName(cursor.getString(cursor.getColumnIndex(KEY_TRANS_NAME)));
            transaction.setAccount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_TRANS_ACC))));
            transaction.setValue(Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_TRANS_VALUE))));
            transaction.setDate(cursor.getString(cursor.getColumnIndex(KEY_TRANS_DATE)));
            transaction.setTime(cursor.getString(cursor.getColumnIndex(KEY_TRANS_TIME)));
            transaction.setNote(cursor.getString(cursor.getColumnIndex(KEY_TRANS_NOTE)));
            allTransactions.add(transaction);
        }
        cursor.close();
        return allTransactions;
    }

    // To delete a specific transaction, pass the transaction's ID to the method.
    public void deleteTransaction(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTIONS, KEY_TRANS_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // To update a transaction, pass the Transaction object to this method which will then update
    // the DB record where the ID matches.
    public int updateTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TRANS_NAME, transaction.getName());
        contentValues.put(KEY_TRANS_ACC, String.valueOf(transaction.getAccount()));
        contentValues.put(KEY_TRANS_VALUE, String.valueOf(transaction.getValue()));
        contentValues.put(KEY_TRANS_DATE, transaction.getDate());
        contentValues.put(KEY_TRANS_TIME, transaction.getTime());
        contentValues.put(KEY_TRANS_NOTE, transaction.getNote());
        int update = db.update(TABLE_TRANSACTIONS, contentValues, KEY_TRANS_ID + " = ?", new String[]{String.valueOf(transaction.getId())});
        return update;
    }

    // To create an Account, pass an Account object to the method.
    public void createAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACC_NAME, account.getName());
        long newRowId = db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    // To get a specific Account, pass the account's ID to this method.
    public Account getAccount(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Account account = new Account();
        String query = "SELECT id, accountName FROM " + TABLE_ACCOUNTS + " WHERE " + KEY_ACC_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()){
            account.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ACC_ID))));
            account.setName(cursor.getString(cursor.getColumnIndex(KEY_ACC_NAME)));
            cursor.close();
        }
        return account;
    }

    // Use this method to get all the Accounts in the Account Table and return them in an ArrayList.
    public ArrayList<Account> getAllAccounts(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Account> allAccounts = new ArrayList<>();
        String query = "SELECT id, accountName FROM " + TABLE_ACCOUNTS + " ORDER BY accountName asc";
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            Account account = new Account();
            account.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ACC_ID))));
            account.setName(cursor.getString(cursor.getColumnIndex(KEY_ACC_NAME)));
            allAccounts.add(account);
        }
        cursor.close();
        return allAccounts;
    }

    // To get the total balance of a specific account, pass the specified account's ID to this method.
    public double getAccountBalance(int accountID){
        SQLiteDatabase db = this.getReadableDatabase();
        double sum = 0.0;
        String query = "SELECT value FROM " + TABLE_TRANSACTIONS + " WHERE " + KEY_TRANS_ACC + " = " + accountID;
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            sum += Double.valueOf(cursor.getString(cursor.getColumnIndex(KEY_TRANS_VALUE)));
        }
        cursor.close();
        return sum;
    }

    // To delete an account, pass the account that you want to delete's method to this method.
    public void deleteAccount(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, KEY_ACC_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // To update a specific account, pass the Account object to this method.
    public int updateAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACC_NAME, account.getName());
        int update = db.update(TABLE_ACCOUNTS, contentValues, KEY_ACC_ID + " = ?", new String[]{String.valueOf(account.getId())});
        return update;
    }

}
