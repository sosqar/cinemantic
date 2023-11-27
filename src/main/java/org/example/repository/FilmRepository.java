package org.example.repository;

import org.example.model.Film;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;

public class FilmRepository {
    private final Logger LOGGER = Logger.getLogger(FilmRepository.class.getName());
    private static final String SQL_SAVE = "INSERT INTO films (title, genre, author, description, votes, " +
            "created_date) VALUES " +
            "(?, ?, ?, ?, ?, TO_TIMESTAMP('2023-11-26 14:30:00', 'YYYY-MM-DD HH24:MI:SS'))";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM films where title =?";
    final String URL = "jdbc:postgresql://localhost:5432/localCinemantic";
    final String USER = "postgres";
    final String PASS = "postgres";

    public void save(Film film) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getGenre());
            preparedStatement.setString(3, film.getAuthor());
            preparedStatement.setString(4, film.getDescription());
            preparedStatement.setInt(5, film.getVotes());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        film.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Ошибка при получении ID фильма.");
                    }
                }
            } else {
                throw new SQLException("Ошибка при создании фильма, ни одна запись не была изменена.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
        }
    }

    public Film findByName(String query) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {

                preparedStatement.setString(1, query);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        return new Film(id, title);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}