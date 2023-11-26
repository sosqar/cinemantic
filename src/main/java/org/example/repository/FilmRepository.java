package org.example.repository;

import org.example.model.Film;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;

public class FilmRepository {
    private final Logger LOGGER = Logger.getLogger(FilmRepository.class.getName());
    String URL = "jdbc:postgresql://localhost:5432/localCinemantic";
    String USER = "postgres";
    String PASS = "postgres";

    public void createFilm(Film film) {
        String sql = "INSERT INTO films (title, genre, author, description, votes, created_date) VALUES (?, ?, ?, ?, ?, TO_TIMESTAMP('2023-11-26 14:30:00', 'YYYY-MM-DD HH24:MI:SS'))";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

//    public Film getFilmById(int id) {
//
//        return null;
//    }
}

//
//    public void updateFilm(Film film) {
//        // Логика для обновления записи в базе данных
//    }
//
//    public void deleteFilm(int id) {
//        // Логика для удаления записи из базы данных по ID
//    }

