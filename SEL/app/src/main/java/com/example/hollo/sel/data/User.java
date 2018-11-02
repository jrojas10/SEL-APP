package com.example.hollo.sel.data;

import android.support.annotation.NonNull;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "UserID")
    private int mUserID;

    @ColumnInfo(name = "FirstName")
    private String FirstName;

    @ColumnInfo(name = "LastName")
    private String LastName;

    @ColumnInfo(name = "UserName")
    private String UserName;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Phone")
    private String Phone;

    @ColumnInfo(name = "Password")
    private String Password;

    @NonNull
    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(@NonNull int mUserID) {
        this.mUserID = mUserID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(int mUserID, String FirstName, String LastName, String UserName, String Email, String Phone, String Password){
        this.mUserID = mUserID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserName = UserName;
        this.Email = Email;
        this.Phone = Phone;
        this.Password = Password;


    }



}
