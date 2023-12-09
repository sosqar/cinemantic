package org.example;


import org.example.impl.ReviewRepoImpl;
import org.example.impl.UserRepoImpl;

public class Main {
    public static void main(String[] args) {
        ReviewRepoImpl rri = new ReviewRepoImpl();
        rri.create("askar.alyiev", "Natus natus corporis officiis.", 5, "Huevyi film");
    }
}

