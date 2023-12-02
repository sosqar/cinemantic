package org.example.repository;

import org.example.config.ConnectionManager;
import org.example.model.Author;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuthorRepository {
    private final Logger LOGGER = Logger.getLogger(AuthorRepository.class.getName());
    private static final String SQL_SAVE = "insert into authors (fullname, created_at) VALUES (?, ?)";

    public void save (Author author) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, author.getFullName());
            preparedStatement.setObject(2, Timestamp.valueOf(LocalDateTime.now()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        author.setId((long) generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Ошибка при получении ID автора.");
                    }
                }
            } else {
                throw new SQLException("Ошибка при сохранении автора, ни одна запись не была изменена.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
        }
    }

}
