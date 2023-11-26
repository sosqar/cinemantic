package org.example.service;

import org.example.model.Film;

import java.util.*;


public class Printer {

    String[] navs = {"Все фильмы", "Выйти"};
    Scanner sc = new Scanner(System.in);

    public Printer() {

    }

    public void printMenu(List<Film> films) {
        System.out.println();
        System.out.println("Добро пожаловать в Cinemantic!\n");
        System.out.println("Главное меню:");
        for (int i = 0; i < navs.length; i++) {
            System.out.println(i + ". " + navs[i]);
        }

        System.out.print("\nВведите номер пункта меню: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 0:
                printFilms(films);
                break;
            case 1:
                System.out.println("\nЖаль что вы покидаете нас ='(");
                break;
            default:
                throw new IllegalStateException("Введите корректный номер пункта меню!");
        }
        if (choice > 1) {
            System.err.println("Введите корректный номер пункта меню!");
            System.out.print("\nВведите номер пункта меню: ");
            choice = sc.nextInt();
        }
    }


    public void printFilms(List<Film> films) {

        System.out.println("\n\n==== Все фильмы ====");

        // Печать заголовка таблицы
        System.out.printf("%4s | %-40s | %-15s | %-30s | %-5s", "ID",
                "Title", "Genre", "Author", "Votes");

        printSeparator(120);

        // Печать данных о фильмах
        for (int i = 0; i < films.size(); i++) {
            System.out.printf("%4s | %-40s | %-15s | %-30s | %5s",
                    (i + 1), films.get(i).getTitle(), films.get(i).getGenre(), films.get(i).getAuthor(),
                    films.get(i).getVotes());
        }
        printSeparator(120);
    }

    public void printSort() {
        // TODO: 26.11.2023  реализовать менюшку для фильтрации и сортировки
    }

    public void emptyArr() {
        System.err.println("Пустой массив");
    }

    public void printSeparator(int number) {
        for (int i = 0; i < number; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

