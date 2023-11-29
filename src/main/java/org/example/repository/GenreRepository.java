package org.example.repository;

import org.example.config.ConnectionManager;
import org.example.model.Genre;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreRepository {
    private final Logger LOGGER = Logger.getLogger(AuthorRepository.class.getName());
    private static final String SQL_SAVE = "insert into genres (name, created_at) VALUES (?, ?)";

    public void save (Genre genre) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, genre.getName());
            preparedStatement.setObject(2, Timestamp.valueOf(LocalDateTime.now()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        genre.setId((long) generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Ошибка при получении ID.");
                    }
                }
            } else {
                throw new SQLException("Ошибка при сохранении, ни одна запись не была изменена.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка: ", e);
        }
    }
}
