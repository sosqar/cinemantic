package org.example.model;

import java.sql.Timestamp;

public class Author {
    private int id;
    private String fullName;
    private Timestamp createdAt;


    public Author (int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
    public Author (String fullName) {
        this.fullName = fullName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nFull Name: " + fullName;
    }
}
