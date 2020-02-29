package com.example.androidtask.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidtask.model.User;

import java.util.List;

@Dao
public interface UsersDao {
    @Insert
    public void insertDetails(User user);

    @Query("SELECT * FROM users")
    public LiveData<List<User>> getUsers();
}