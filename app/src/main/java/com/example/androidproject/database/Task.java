package com.example.androidproject.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(tableName="Task")
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    //private Date date;
    private Boolean done;

    //private Bitmap photo;
    private double latitude;

    private double longitude;

    public Task () {
        // date = new Date();
        name = "";
        done = false;
    }
    @Ignore
    public Task (String name) {
        //date = new Date();
        this.name = name;
        done = false;
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

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

//    @Nullable
//    public Bitmap getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(@Nullable Bitmap photo) {
//        this.photo = photo;
//    }

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