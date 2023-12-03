package org.example.service;

import com.github.javafaker.Faker;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Randomizer {
    Faker faker = new Faker();

    public String getTitle() {
        return faker.lorem().sentence(1, 4);
    }
    public String getUsername() {
        return faker.name().username();
    }

    public String getId() {
        return faker.internet().uuid();
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

    public Timestamp getCreated_at() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public String getFullName() {
        return faker.name().fullName();
    }
}
