package org.example.model;

import java.sql.Timestamp;

public class Review {

    private String id;
    private String userId;
    private String filmId;
    private int value;
    private String description;
    private Timestamp createdAt;

    public Review(String id, String userId, String filmId, int value, String description, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.filmId = filmId;
        this.value = value;
        this.description = description;
        this.createdAt = createdAt;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
