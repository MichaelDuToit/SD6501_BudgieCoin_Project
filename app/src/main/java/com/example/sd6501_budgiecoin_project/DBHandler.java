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

    public void createTransaction(String name, double value, String date, String time, int account, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRANS_NAME, name);
        values.put(KEY_TRANS_VALUE, value);
        values.put(KEY_TRANS_DATE, date);
        values.put(KEY_TRANS_TIME, time);
        values.put(KEY_TRANS_ACC, String.valueOf(account));
        values.put(KEY_TRANS_NOTE, note);
        long newRowId = db.insert(TABLE_TRANSACTIONS, null, values);
        db.close();
    }

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

    public ArrayList<Transaction> getAllTransactions(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        String query = "SELECT id, transactionName, value, account, note, date, time FROM " + TABLE_TRANSACTIONS;
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

    public void deleteTransaction(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTIONS, KEY_TRANS_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int updateTransaction(int id, String name, double value, String date, String time, Account account, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TRANS_NAME, name);
        contentValues.put(KEY_TRANS_ACC, String.valueOf(account));
        contentValues.put(KEY_TRANS_VALUE, String.valueOf(value));
        contentValues.put(KEY_TRANS_DATE, date);
        contentValues.put(KEY_TRANS_TIME, time);
        contentValues.put(KEY_TRANS_NOTE, note);
        int update = db.update(TABLE_TRANSACTIONS, contentValues, KEY_TRANS_ID + " = ?", new String[]{String.valueOf(id)});
        return update;
    }

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

    public void createAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACC_NAME, account.getName());
        long newRowId = db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }


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

    public ArrayList<Account> getAllAccounts(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Account> allAccounts = new ArrayList<>();
        String query = "SELECT id, accountName FROM " + TABLE_ACCOUNTS;
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

    public void deleteAccount(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, KEY_ACC_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int updateAccount(int id, String accountName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACC_NAME, accountName);
        int update = db.update(TABLE_ACCOUNTS, contentValues, KEY_ACC_ID + " = ?", new String[]{String.valueOf(id)});
        return update;
    }

}
