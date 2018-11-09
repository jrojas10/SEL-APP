package com.example.hollo.sel.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

// For more information on how to use DAOs with Room, see https://developer.android.com/training/data-storage/room/accessing-data

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    //If conflicts are possible, you can use @Insert(onConflict = OnConflictStrategy.REPLACE)

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * FROM user_table ORDER BY user ASC")
    LiveData<List<User>> getAllUsers();
}
