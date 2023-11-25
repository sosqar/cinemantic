package org.example;

import org.example.model.Film;
import org.example.model.FilmCollection;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FilmCollection films = new FilmCollection();
        films.generate();
        films.getFilms();

    }
}