package org.example.service;

import com.github.javafaker.Faker;
import org.example.model.Film;

import java.text.DecimalFormat;
import java.util.*;


public class Randomizer {
    Faker faker = new Faker();

    public Randomizer() {

    }

    public String getId() {
        int length = 5;
        String id;
        id = UUID.randomUUID()
                .toString()
                .substring(0, length);
        return id;
    }

    public String getTitle() {
        return faker.lorem().sentence(1, 3);
    }


    public String getGenre() {
        String[] genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Science fiction", "Fantasy"
                , "Historical", "Crime"};
        int number = getNumber(0, 9);
        return genres[number];
    }

    public String getAuthor() {
        return faker.name().fullName();
    }

    public String getDescription() {
        return faker.lorem().paragraph();
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

    public String getRating() {
        List<Integer> votes = getVotes();

        if (!votes.isEmpty()) {
            int rating = 0;

            for (int vote : votes) {
                rating += vote;
            }
            double rate = (double) rating / votes.size();
            DecimalFormat df = new DecimalFormat("#.0");
            return df.format(rate);
        } else {
            return null;
        }
    }

    public Film getFilm() {
        Film film = new Film();
        film.setId(getId());
        film.setTitle(getTitle());
        film.setGenre(getGenre());
        film.setDescription(getDescription());
        film.setAuthor(getAuthor());
        film.setVotes(getNumber(1, 10));
        film.setRating(getRating());

        return film;
    }

}
