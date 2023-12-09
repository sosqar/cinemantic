package org.example.model;

import java.sql.Timestamp;

public class User {
    private String id;
    private String username;
    private Timestamp createdAt;

    public User() {
    }
    public User(String id, String username, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
    }

    public User(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "\nid: " + id + ',' + " username: " + username;
    }
}
