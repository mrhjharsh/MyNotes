package com.example.notesapplicationjava;

import io.realm.RealmObject;

public class Task extends RealmObject {
    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long key;
     private String title;
     private String description;

    public Task(String title, String description) {
        this.title = description;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Task() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
