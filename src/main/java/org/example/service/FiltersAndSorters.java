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

    public void getFilmsByFilterType(int choice) {
        switch (choice) {
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
        }
        films.sort(Comparator.comparing(Film::getRating).reversed());
        System.out.println("\nФильмы, отсортированные по рейтингу:\n");
        System.out.printf("%-5s | %-40s | %-5s%n", "ID", "Title", "Rating");
        printer.printSeparator(10);

        // Печать данных о фильмах
        for (Film film : films) {
            System.out.printf("%-5s | %-40s | %-5s%n",
                    film.getId(), film.getTitle(), film.getRating());
        }
        printer.printSeparator(10);
    }


    private void getSortedFilmsByVotes() {
        if (films.isEmpty()) {
            printer.emptyArr();
        }

        films.sort(Comparator.comparing(Film::getVotes).reversed());
        System.out.println("\nФильмы, отсортированныепо количеству оценок:\n");
        System.out.printf("%-5s | %-40s | %-5s%n", "ID", "Title", "Votes");
        printer.printSeparator(10);

        for (Film film : films) {
            System.out.printf("%-5s | %-40s | %-5s%n", film.getId(), film.getTitle(), film.getVotes());
        }
        printer.printSeparator(10);

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
            System.out.println("\nФильмы с выбранным жанром отсутствуют.\n");
        } else {
            System.out.println("\nФильмы, отфильтрованные по жанру " + selectedGenre + ":\n");
            System.out.printf("%-5s | %-40s | %-15s | %-5s | %-5s%n", "ID", "Title", "Genre", "Rating", "Votes");
            printer.printSeparator(10);
            for (Film filteredFilm : filteredFilms) {
                System.out.printf("%-5s | %-40s | %-15s | %-5s | %-5s%n", filteredFilm.getId(),
                        filteredFilm.getTitle(), filteredFilm.getGenre(),
                        filteredFilm.getRating(), filteredFilm.getVotes());
            }
        }
    }
}

