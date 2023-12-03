package org.example;

import org.example.impl.UserRepositoryImpl;


public class Main {
    public static void main(String[] args) {
        UserRepositoryImpl uri = new UserRepositoryImpl();
        System.out.println(uri.showAll());
    }
}

