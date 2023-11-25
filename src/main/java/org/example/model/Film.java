package org.example.model;

import java.math.BigDecimal;


public class Film {
    private String id, title, genre, author, description;
    private int votes;
    private BigDecimal rating;

    public Film() {

    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", votes=" + votes +
                ", rating=" + rating +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public int getVotes() {
        return votes;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


