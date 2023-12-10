package org.example.model;


import java.sql.Timestamp;

public class Film {
    private String id;
    private String title;
    private String genre;
    private String description;
    private Timestamp createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public Film() {
    }
    public Film(String id, String title, String genre, String description, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '}';
    }

}





