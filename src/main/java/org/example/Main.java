package org.example;

import org.example.model.Film;
import org.example.repository.FilmRepository;


public class Main {


    public static void main(String[] args) {
        FilmRepository fr = new FilmRepository();
        Film film = fr.updateFilm("Thriller / Horror", "Azhara Izbasarova", "На Веранде происходят странные вещи, аж кровь стынет.", 22);

    }
//Init
//        List<Film> films = new FilmRepository1().getFilms();
//        Printer printer = new Printer();
//        FiltersAndSorters fas = new FiltersAndSorters(films);
//
//        printer.printMenu(films);
//
//
//        Scanner sc = new Scanner(System.in);
//        String[] types = {"По рейтингу", "По количеству оценок", "По жанру", "Выйти"};
//        while (true) {
//            System.out.println("\nВыберите тип сортировки:");
//            for (int i = 0; i < types.length; i++) {
//                System.out.println((i + ": " + types[i]));
//            }
//            int choice = sc.nextInt();
//            if (choice == 3) {
//                break;
//            }
//            fas.getFilmsByFilterType(choice);
//            System.out.println();
//        }
}