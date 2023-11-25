package org.example.model;

import org.example.service.Randomizer;

import java.util.HashMap;
import java.util.Map;

public class FilmCollection {
    private final Map<Integer, Film> films = new HashMap<Integer, Film>();

    public FilmCollection() {
        init();
    }
    public void init() {
        for (int i = 1; i <= 100; i++) {
            Randomizer random = new Randomizer();
            Film film = random.getFilm();
            films.put(i, film);
        }
    }

    public void getFilms() {
        for (Map.Entry<Integer, Film> item : films.entrySet()) {
            int filmNumber = item.getKey();
            Film film = item.getValue();
            System.out.println(filmNumber + " " + film.toString());
        }
    }

    public void getSortedFilmsByName() {
        for (Map.Entry<Integer, Film> item : films.entrySet()) {
            Integer filmNumber = item.getKey();
            Film film = item.getValue();
            System.out.println(filmNumber + " " + film.toString());
        }
    }

}

