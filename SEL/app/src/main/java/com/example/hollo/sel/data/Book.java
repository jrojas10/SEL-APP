package com.example.hollo.sel.data;

import android.support.annotation.NonNull;

@Entity(tableName = "Books")
public class Book {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "BookID")
    private int mBookID;

    @columnInfo(name="Price")
    private  double Price;

    @columnInfo(name = "Title")
    private String Title;

    @columnInfo(name = "ISBN")
    private String ISBN;

    @columnInfo(name = "AuthorFirst")
    private String AuthorFirst;

    @columnInfo(name = "AuthorLast")
    private String AuthorLast;

    @columnInfo(name = "Subject")
    private String Subject;

    @columnInfo(name = "Class")
    private String Class;

    @columnInfo(name = "State")
    private String State;

    @NonNull
    public int getmBookID() {
        return mBookID;
    }

    public void setmBookID(@NonNull int mBookID) {
        this.mBookID = mBookID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthorFirst() {
        return AuthorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        AuthorFirst = authorFirst;
    }

    public String getAuthorLast() {
        return AuthorLast;
    }

    public void setAuthorLast(String authorLast) {
        AuthorLast = authorLast;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public String getClass() {
        return Class;
    }

    public void setClass(String aClass) {
        Class = aClass;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Book (int mBookID, double Price, String Title, String ISBN, String AuthorFirst, String AuthorLast, String Subject, String Class, String State){
        this.mBookID = mBookID;
        this.Price = Price;
        this.Title = Title;
        this.ISBN = ISBN;
        this.AuthorFirst = AuthorFirst;
        this.AuthorLast = AuthorLast;
        this.Subject = Subject;
        this.Class = Class;
        this.State = State;

    }



}
