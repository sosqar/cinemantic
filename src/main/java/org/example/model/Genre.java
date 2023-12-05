package org.example.model;

import java.sql.Timestamp;

public class Genre {
    private String id;
    private String name;
    private Timestamp createdAt;


    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id = '" + id + '\'' +
                ", name = '" + name + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
