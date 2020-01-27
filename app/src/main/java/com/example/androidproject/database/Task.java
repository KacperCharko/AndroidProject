package com.example.androidproject.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity(tableName="Task")
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Boolean done;
    //private Bitmap photo;
    private double latitude;

    private double longitude;


    public Task (String name) {

        this.name = name;
        this.latitude=0;
        this.latitude=0;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



}