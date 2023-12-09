package org.example.impl;

import org.example.config.ConnectionManager;
import org.example.model.Genre;
import org.example.service.GenreRepo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreRepoImpl implements GenreRepo {
    private final Logger LOGGER = Logger.getLogger(GenreRepo.class.getName());
    private static final String SQL_CREATE = "insert into genres (genre, created_at) VALUES (?, ?)";
    private static final String SQL_DELETE = "delete from genres where lower(genre) = lower(?)";
    private static final String SQL_FIND = "select * from genres where lower(genre) = lower(?)";
    private static final String SQL_FIND_ALL = "select * from genres";
    private static final String SQL_GET_ID = "select id from genres where lower(genre) = lower(?)";


    @Override
    public Genre create(String genre) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, genre);
            preparedStatement.setObject(2, Timestamp.valueOf(LocalDateTime.now()));
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        String name = generatedKeys.getString(2);
                        return new Genre(id, name);
                    } else {
                        LOGGER.warning("Ошибка при получении значений.");
                    }
                }
            } else {
                LOGGER.warning("Ошибка при сохранении, попробуйте снова");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void delete(String genre) {

        try (Connection connection = ConnectionManager.getConnect()) {
            Genre genreToDelete = find(genre);
            if (genreToDelete != null) {
                try {
                    connection.setAutoCommit(false); // Начало транзакции
                    try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE)) {
                        prepareStatement.setString(1, genre);
                        int affectedRows = prepareStatement.executeUpdate();
                        if (affectedRows <= 0) {
                            LOGGER.warning("Ошибка при удалении жанра.");
                        }
                    }
                    connection.commit(); // Завершение транзакции
                    LOGGER.log(Level.SEVERE, "Жанр: " + genreToDelete.getId() + " успешно удален.");
                } catch (SQLException e) {
                    connection.rollback(); // Откат транзакции в случае ошибки
                    LOGGER.log(Level.SEVERE, "Ошибка при выполнении транзакции", e);
                    throw new RuntimeException(e);
                } finally {
                    connection.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Genre find(String genre) {
        try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_FIND)) {
            preparedStatement.setString(1, genre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("genre");
                    return new Genre(id, name);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("genre");
                    Timestamp created_at = resultSet.getTimestamp("created_at");

                    Genre genre = new Genre(id, name);
                    genres.add(genre);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    public Integer getId(String genre) {
        try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_GET_ID)) {
            preparedStatement.setString(1, genre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    return id;
                }
            } catch (SQLException e) {
                LOGGER.warning("Ошибка при получении id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
