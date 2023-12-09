package org.example.service;

import org.example.model.Review;

import java.util.List;

public interface ReviewRepo{

    Review create(String userId, String filmId, int value, String description);
    Review update(String id, String username);
    Review findById(String id);


    Review deleteById(String id);
    List<Review> showAll();


}
