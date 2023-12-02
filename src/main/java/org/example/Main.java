package org.example;

import org.example.model.Genre;
import org.example.repository.GenreRepository;


public class Main {
    public static void main(String[] args) {
        GenreRepository gr = new GenreRepository();


        System.out.println(gr.findByName("Romance"));
    }
}


