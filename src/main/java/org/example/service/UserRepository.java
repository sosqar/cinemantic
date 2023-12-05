package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserRepository {
    User create(String username);
    User update(String id, String username);
    User findById(String id);
    User findByUsername(String username);
    User deleteById(String id);
    User deleteByUsername(String username);
    List<User> showAll();

}
