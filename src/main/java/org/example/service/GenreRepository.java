package org.example.service;

import org.example.model.Genre;
import org.example.model.User;

import java.util.List;

public interface GenreRepository {
    void create(String genre);
    Genre update(String id, String genre);
    Genre findById(String id);
    Genre findByName(String username);
    Genre deleteById(String id);
    void deleteByUsername(String username);
    List<User> showAll();
}
