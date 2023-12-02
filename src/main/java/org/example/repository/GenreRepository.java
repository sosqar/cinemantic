package org.example.repository;

import org.example.config.ConnectionManager;
import org.example.model.Genre;

import java.sql.*;
import java.time.LocalDateTime;

public class GenreRepository {
    private static final String SQL_SAVE = "insert into genres (name, created_at) VALUES (?, ?)";
    private static final String SQL_FIND_BY_NAME = "select * from genres where name = ?";
    private static final String SQL_DELETE_BY_ID = "delete from genres where id = ?";

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
            throw new RuntimeException(e);
        }
    }
    public Genre findByName(String query) {
        try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    return new Genre((long) id, name);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Genre deleteById(String query) {
        Genre deletedGenre = findByName(query);

        if (deletedGenre != null) {
            try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_DELETE_BY_ID)) {

                preparedStatement.setString(1, query);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows <= 0) {
                    throw new SQLException("Ошибка при удалении жанра. Ни одна запись не была изменена");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Жанр успешно удален:");
            System.out.println("ID: " + deletedGenre.getId());
            System.out.println("Title: " + deletedGenre.getName());
        }
        return deletedGenre;
    }
}
