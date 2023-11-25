package org.example.model;


import java.util.Scanner;

public class Dropdown {
    private String filterType;

    public Dropdown() {
    }

    public static void main(String[] args) {
        Dropdown dd = new Dropdown();
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

    public int chooseGenre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите жанр:");
        String[] genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Science fiction",
                "Fantasy"
                , "Historical", "Crime"};
        for (int i = 0; i < genres.length; i++) {
            System.out.println(i + ": " + genres[i]);
        }
        return sc.nextInt();
    }
}
