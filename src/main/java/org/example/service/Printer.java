package org.example.service;

import org.example.model.Film;

import java.util.List;


public class Printer {
    public Printer() {

    }
    public void emptyArr() {
        System.err.println("Пустой массив");
    }

    public void printFilmsTable(List<Film> films) {
        // Печать заголовка таблицы
        System.out.printf("%-5s | %-40s | %-15s | %-30s | %-5s | %-5s%n",
                "ID", "Title", "Genre", "Author", "Votes", "Rating");
        printSeparator();

        // Печать данных о фильмах
        for (Film film : films) {
            System.out.printf("%-5s | %-40s | %-15s | %-30s | %-5s | %-5s%n",
                    film.getId(), film.getTitle(), film.getGenre(), film.getAuthor(), film.getVotes(),
                    film.getRating());
        }
        printSeparator();
    }
    public void printSeparator() {
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}

