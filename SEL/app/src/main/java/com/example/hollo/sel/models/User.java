package com.example.hollo.sel.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*
Add these annotations
@Entity(tableName = "word_table")
   - Each @Entity class represents an entity in a table. Annotate your class declaration to indicate that it's an entity. Specify the name of the table if you want it to be different from the name of the class.
@PrimaryKey
   - Every entity needs a primary key. To keep things simple, each word acts as its own primary key.
@NonNull
   - Denotes that a parameter, field, or method return value can never be null.
@ColumnInfo(name = "word")
   - Specify the name of the column in the table if you want it to be different from the name of the member variable.
   - Every field that's stored in the database needs to be either public or have a "getter" method. This sample provides a getWord() method.
* */

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user")
    private String mUser;

    public User(@NonNull String user) {
        this.mUser = user;
    }

    public String getUser() {
        return this.mUser;
    }
}