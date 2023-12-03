package org.example;

import org.example.impl.UserRepositoryImpl;


public class Main {
    public static void main(String[] args) {
        UserRepositoryImpl uri = new UserRepositoryImpl();
        System.out.println(uri.update("bb2860cf-66f7-455e-829d-7d5629e3", "askar.alyiev"));
    }
}

