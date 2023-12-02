package org.example.model;


import java.sql.Timestamp;

public class Film {
    private int id;
    private String title;
    private String genre;
    private String author;
    private String description;
    private double rating;
    private int votes;
    private Timestamp createdAt;

    public Film() {
    }
    public Film(int id, String title, String genre, String author, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.description = description;
    }
    @Override
    public String toString() {
        return "{title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", votes=" + votes + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getRating() {
//        return rating;
//    }

//    public void setRating(String rating) {
//        this.rating = rating;
//    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Timestamp getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt;
    }
}





