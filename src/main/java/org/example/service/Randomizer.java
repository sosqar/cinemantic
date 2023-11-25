package org.example.service;

import org.example.model.Film;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class Randomizer {

    public Randomizer() {

    }

    public String getTitle() {
        String title = "Title ";
        int number = getNumber(1, 1000);
        return title + number;
    }

    public String getGenre() {
        String genre = "Genre ";
        int number = getNumber(1, 1000);
        return genre + number;
    }

    public String getAuthor() {
        String author = "Author ";
        int number = getNumber(1, 1000);
        return author + number;
    }

    public int getNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }


    public List<Integer> getVotes() {
        List<Integer> votes = new ArrayList<>();
        int votesCount = getNumber(1, 10);

        for (int i = 0; i < votesCount; i++) {
            votes.add(getNumber(1, 10));
        }
        return votes;
    }

    public BigDecimal getRating() {
        List<Integer> votes = getVotes();

        if (!votes.isEmpty()) {
            int rating = 0;

            for (int vote : votes) {
                rating += vote;
            }
            double avg = (double) rating / votes.size();
            BigDecimal result = new BigDecimal(avg);
            result = result.setScale(1, RoundingMode.DOWN);
            return result;
        } else {
            return null;
        }
    }

    public Film getFilm() {
        Film film = new Film();
        film.setTitle(getTitle());
        film.setGenre(getGenre());
        film.setAuthor(getAuthor());
        film.setVotes(getNumber(1, 10));
        film.setRating(getRating());

        return film;
    }

}
