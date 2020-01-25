package com.example.androidproject;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Task  implements Serializable {
    private UUID id;
    private String name;
    private Date date;
    private Boolean done;
    @Nullable
    private Bitmap photo;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(@Nullable Bitmap photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }



    public Task () {
        id = UUID.randomUUID();
        date = new Date();
        name = "";
        done = false;
    }

    public Task (String name) {
        id = UUID.randomUUID();
        date = new Date();
        this.name = name;
        done = false;
    }
}
