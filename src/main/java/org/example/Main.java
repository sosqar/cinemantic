package org.example;


import org.example.model.Film;
import org.example.service.Randomizer;

public class Main {
    public static void main(String[] args) {
        System.err.println("Hello and welcome!\n");

        for (int i = 1; i <= 5; i++) {
            Randomizer random = new Randomizer();
            Film film = random.getFilm();
            System.out.println(film);
        }
    }
}