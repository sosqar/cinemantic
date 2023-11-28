//package org.example.repository;
//
//import org.example.model.Film;
//import org.example.service.Randomizer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class OldFilmRepository {
//    private final List<Film> films = new ArrayList<>();
//
//    public OldFilmRepository() {
//        init();
//    }
//
//    private void init() {
//        for (int i = 1; i <= 5; i++) {
//            Randomizer random = new Randomizer();
//            Film film = random.getFilm();
//            films.add(film);
//        }
//    }
//
//    public List<Film> getFilms() {
//        return films;
//    }
//}
//
