package com.example.androidproject;

import java.util.Date;
import java.util.UUID;

public class Task    {
    private UUID id;
    private String name;
    private Date date;
    private Boolean done;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
