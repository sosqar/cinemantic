package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserRepository {
    void create(String username);
    User update(String id, String username);
    User findById(String id);
    User findByUsername(String username);
    void deleteById(String id);
    void deleteByUsername(String username);
    List<User> showAll();

}
