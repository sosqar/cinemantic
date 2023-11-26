package org.example.service;

import org.example.model.Film;
import org.example.model.FilmList;
import org.example.service.Printer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FiltersAndSorters {
    private final List<Film> films;
    Printer printer = new Printer();

    public FiltersAndSorters(List<Film> films) {
        this.films = films;
    }
    public void makeChoice() {
        switch (getSortType()) {
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
    public int getSortType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите тип сортировки:");

        String[] types = {"по рейтингу", "по количеству оценок", "по жанру"};
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + ": " + types[i]));
        }
        return sc.nextInt();
    }

    public void getSortedFilmsByRating() {
        if(films.isEmpty()) {
            printer.emptyArr();
        } else {
            films.sort(Comparator.comparing(Film::getRating).reversed());
            System.out.println("Фильмы, отсортированные по рейтингу:");
            for (int i = 0; i < films.size(); i++) {
                System.out.println((i + 1) + ": " + films.get(i));
            }
        }

    }

    public void getSortedFilmsByVotes() {
        if(films.isEmpty()) {
            printer.emptyArr();
        } else {
            films.sort(Comparator.comparing(Film::getVotes).reversed());
            System.out.println("Фильмы, отсортированныепо количеству оценок:");
            for (int i = 0; i < films.size(); i++) {
                System.out.println((i + 1) + ": " + films.get(i));
            }
        }
    }

    public void getFiltredFilmsByGenre() {
        if(films.isEmpty()) {
            printer.emptyArr();
        } else {
            String[] genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Science fiction", "Fantasy", "Historical", "Crime"};

            Scanner sc = new Scanner(System.in);
            System.out.println("Выберите жанр:");
            for (int i = 0; i < genres.length; i++) {
                System.out.println(i + ": " + genres[i]);
            }
            String selectedGenre = genres[Integer.parseInt(sc.nextLine())];

            List<Film> filteredFilms = films.stream()
                    .filter(film -> film.getGenre().equalsIgnoreCase(selectedGenre))
                    .collect(Collectors.toList());

            if (filteredFilms.isEmpty()) {
                System.out.println("Фильмы с выбранным жанром отсутствуют.");
            } else {
                System.out.println("Фильмы, отфильтрованные по жанру " + selectedGenre + ":");
                for (Film filteredFilm : filteredFilms) {
                    System.out.println(filteredFilm);
                }
            }
        }

    }
}

