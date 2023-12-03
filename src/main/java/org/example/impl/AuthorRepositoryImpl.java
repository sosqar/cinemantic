package org.example.impl;

import org.example.config.ConnectionManager;
import org.example.model.Author;
import org.example.service.AuthorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AuthorRepositoryImpl implements AuthorRepository{
    private static final Logger LOGGER = Logger.getLogger(AuthorRepository.class.getName());
    private static final String SQL_SAVE = "insert into authors (fullname, created_at) VALUES (?, ?)";
    private static final String SQL_FIND_BY_NAME = "select * from authors where fullName = ?";
    private static final String SQL_IS_FULLNAME_UNIQUE = "select count(*) from authors where fullName = ?";


    public Author findByName(String query) {
        try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String fullName = resultSet.getString("fullName");
                    return new Author(fullName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private boolean isAuthorNameUnique(String fullName) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_FULLNAME_UNIQUE)) {
            preparedStatement.setString(1, fullName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при проверке уникальности имени автора.");
        }
        return false;
    }

}
