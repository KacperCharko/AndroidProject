package com.example.androidproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update
    public void update(Task task);

    @Delete
    public void delete(Task task);

    @Query("DELETE FROM task")
    public void deleteAll();

    @Query("SELECT * FROM Task order by name")
    public LiveData<List<Task>> findAll();

//    @Query("SELECT * FROM Task WHERE name = :name")
//    public abstract Task findByName(String name);
}