package org.example.model;

import org.example.service.Randomizer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class FilmCollection {
    private final List<Film> films = new ArrayList<>();
    List<Film> sortedFilms = new ArrayList<>(films);
    private String[] genres;


    public static void main(String[] args) {
        FilmCollection filmCollection = new FilmCollection();
        Dropdown dd = new Dropdown();
        int sortType = dd.getSortType();
        switch (sortType) {
            case 0:
                filmCollection.getSortedFilmsByRating();
                break;
            case 1:
                filmCollection.getSortedFilmsByVotes();
                break;
            case 2:
                filmCollection.getFiltredFilmsByGenre();
                break;
        }


    }

    public FilmCollection() {
        init();

    }

    public void init() {
        for (int i = 1; i <= 10; i++) {
            Randomizer random = new Randomizer();
            Film film = random.getFilm();
            films.add(film);
        }
    }

    public void getFilms() {
        System.out.println("Список фильмов:");
        for (int i = 0; i < films.size(); i++) {
            System.out.println((i + 1) + ": " + films.get(i));
        }
        System.out.println();
    }

    public void getSortedFilmsByRating() {
//        List<Film> sortedFilms = new ArrayList<>(films);
        sortedFilms.sort(Comparator.comparing(Film::getRating).reversed());

        System.out.println("Фильмы, отсортированные по рейтингу:");
        for (int i = 0; i < sortedFilms.size(); i++) {
            System.out.println((i + 1) + ": " + sortedFilms.get(i));
        }
    }

    public void getSortedFilmsByVotes() {
        List<Film> sortedFilms = new ArrayList<>(films);
        sortedFilms.sort(Comparator.comparing(Film::getVotes).reversed());
        System.out.println("Фильмы, отсортированныепо количеству оценок:");
        for (int i = 0; i < sortedFilms.size(); i++) {
            System.out.println((i + 1) + ": " + sortedFilms.get(i));
        }
    }

    public void getGenres() {
        List<Film> sortedFilms = new ArrayList<>(films);
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите жанр:");
        String[] genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Science fiction",
                "Fantasy"
                , "Historical", "Crime"};
        for (int i = 0; i < genres.length; i++) {
            System.out.println(i + ": " + genres[i]);
        }
        sc.nextInt();


    }

    public void getFiltredFilmsByGenre() {
        Scanner sc = new Scanner(System.in);
        List<Film> sortedFilms = new ArrayList<>(films);

        System.out.println("Выберите жанр:");
        String[] genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Science fiction", "Fantasy", "Historical", "Crime"};

        for (int i = 0; i < genres.length; i++) {
            System.out.println(i + ": " + genres[i]);
        }

        int selectedGenreIndex = Integer.parseInt(sc.nextLine());

        String selectedGenre = getGenreByIndex(selectedGenreIndex);

        List<Film> filteredFilms = sortedFilms.stream()
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

