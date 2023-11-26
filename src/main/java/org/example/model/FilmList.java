package org.example.model;

import org.example.service.Randomizer;

import java.util.ArrayList;
import java.util.List;



public class FilmList {
    private final List<Film> films = new ArrayList<>();

    public FilmList() {
        init();
    }
    private void init() {
        for (int i = 1; i <= 10; i++) {
            Randomizer random = new Randomizer();
            Film film = random.getFilm();
            films.add(film);
        }
    }
    public List<Film> getFilms() {
        return films;
    }
}

