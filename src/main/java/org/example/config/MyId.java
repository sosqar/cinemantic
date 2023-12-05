package org.example.config;

import java.util.UUID;

public class MyId {
    final int length = 32;
    public String parse() {
        String uuid;
        uuid = UUID.randomUUID()
                .toString()
                .substring(0, length);
        return uuid;
    }

}
