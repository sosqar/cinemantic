package org.example.impl;

import org.example.service.ReviewRepository;

import java.util.UUID;
import java.util.logging.Logger;

public class ReviewRepositoryImpl implements ReviewRepository {
    private final Logger LOGGER = Logger.getLogger(ReviewRepository.class.getName());
    private static final String SQL_SAVE = "insert into genres (name, created_at) VALUES (?, ?)";
    private static final String SQL_FIND_BY_NAME = "select * from genres where name = ?";
    private static final String SQL_DELETE_BY_ID = "delete from genres where id = ?";

    public String getId() {
        int length = 32;
        String id;
        id = UUID.randomUUID()
                .toString()
                .substring(0, length);
        return id;
    }
}
