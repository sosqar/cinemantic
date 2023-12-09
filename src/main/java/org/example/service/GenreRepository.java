package org.example.service;

import org.example.model.Genre;

import java.util.List;

public interface GenreRepo {
    Genre create(String genre);
    Genre find(String genre);
    Integer getId(String genre);
    List<Genre> findAll();
    void delete(String genre);


}
