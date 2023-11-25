package org.example.model;

import java.math.BigDecimal;

public class Film {
    private String title, genre, author;
    private int votes;
    private BigDecimal rating;

    @Override
    public String toString() {
        return "\nTitle: " + title +
                "\nGenre: " + genre +
                "\nAuthor: " + author +
                "\nVotes: " + votes +
                "\nRating: " + rating;
    }

    public Film() {

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
}



