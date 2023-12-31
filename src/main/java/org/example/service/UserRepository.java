package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserRepo {
    User create(String username);
    User update(String id, String username);
    User findById(String id);
    User findByUsername(String username);
    String getUserIdByUsername(String username);
    User deleteByUsername(String username);
    List<User> showAll();

}
