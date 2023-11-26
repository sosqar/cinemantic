package org.example.service;

import org.example.model.Film;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FiltersAndSorters {
    private final List<Film> films;
    Printer printer = new Printer();
    private final String[] genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Science fiction",
            "Fantasy", "Historical", "Crime"};

    public FiltersAndSorters(List<Film> films) {
        this.films = films;
    }

    public void getFilmsByFilterType(int filterType) {
        switch (filterType) {
            case 0:
                getSortedFilmsByRating();
                break;
            case 1:
                getSortedFilmsByVotes();
                break;
            case 2:
                getFiltredFilmsByGenre();
                break;
        }
    }

    private void getSortedFilmsByRating() {
        if (films.isEmpty()) {
            printer.emptyArr();
        } else {
            films.sort(Comparator.comparing(Film::getRating).reversed());
            System.out.println("Фильмы, отсортированные по рейтингу:");

            for (int i = 0; i < films.size(); i++) {
                Film film = films.get(i);
                System.out.println((i + 1) + ": Title: " + film.getTitle() + " Rating: " + film.getRating());
            }
        }

    }

    private void getSortedFilmsByVotes() {
        if (films.isEmpty()) {
            printer.emptyArr();
        }
        films.sort(Comparator.comparing(Film::getVotes).reversed());
        System.out.println("Фильмы, отсортированныепо количеству оценок:");
        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);
            System.out.println((i + 1) + ": Title: " + film.getTitle() + "Votes: " + film.getVotes());
        }

    }

    private void getFiltredFilmsByGenre() {
        if (films.isEmpty()) {
            printer.emptyArr();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите жанр:");
        for (int i = 0; i < genres.length; i++) {
            System.out.println(i + ": " + genres[i]);
        }
        String selectedGenre = genres[Integer.parseInt(sc.nextLine())];
        List<Film> filteredFilms = films.stream()
                .filter(film -> film.getGenre().equalsIgnoreCase(selectedGenre))
                .toList();

        if (filteredFilms.isEmpty()) {
            System.out.println("Фильмы с выбранным жанром отсутствуют.");
        }
        System.out.println("Фильмы, отфильтрованные по жанру " + selectedGenre + ":");
        for (Film filteredFilm : filteredFilms) {
            System.out.println(filteredFilm);
        }


    }
}

