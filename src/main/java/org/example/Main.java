package org.example;

import org.example.impl.UserRepositoryImpl;
import org.example.model.User;


public class Main {
    public static void main(String[] args) {
        UserRepositoryImpl uri = new UserRepositoryImpl();
        uri.create("askar.aly");
        System.out.println();
    }
}

