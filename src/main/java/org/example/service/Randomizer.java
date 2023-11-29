package org.example.service;

import com.github.javafaker.Faker;
import org.example.model.Author;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Randomizer {
    Faker faker = new Faker();


    public String getId() {
        int length = 32;
        String id;
        id = UUID.randomUUID()
                .toString()
                .substring(0, length);
        return id;
    }

    public String getTitle() {
        return faker.lorem().sentence(1, 4);
    }




    public String getDescription() {
        return faker.lorem().paragraph();
    }


    public int getNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }


    public int getVotes() {
        List<Integer> votes = new ArrayList<>();
        int votesCount = getNumber(1, 500);

        for (int i = 0; i < votesCount; i++) {
            votes.add(getNumber(1, 5));
        }
        return votes.size();
    }

//    public String getRating() {
//
//        if (!votes.isEmpty()) {
//            int rating = 0;
//
//            for (int vote : votes) {
//                rating += vote;
//            }
//            double rate = (double) rating / votes.size();
//            DecimalFormat df = new DecimalFormat("#.0");
//            return df.format(rate);
//        } else {
//            return null;
//        }
//    }

    public Timestamp getCreated_at() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public String getFullName() {
        return faker.name().fullName();
    }

//    public Film getFilm() {
//        Film film = new Film();
//        film.setTitle(getTitle());

//        film.setDescription(getDescription());
//        film.setVotes(getVotes());
//
//        return film;
//    }

    public Author getAuthor() {

        Author author = new Author();
        author.setFullName(getFullName());

        return author;
    }
}
