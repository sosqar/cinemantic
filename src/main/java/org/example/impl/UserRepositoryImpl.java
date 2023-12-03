package org.example.impl;

import org.example.config.ConnectionManager;
import org.example.model.Film;
import org.example.model.User;
import org.example.service.UserRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserRepositoryImpl implements UserRepository {
    final int length = 32;
    private final Logger LOGGER = Logger.getLogger(FilmRepositoryImpl.class.getName());
    private static final String SQL_SAVE = "insert into users (id, username, created_at) VALUES (?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "select * from users where id = ?";
    private static final String SQL_FIND_BY_USERNAME = "select * from users where username =?";
    private static final String SQL_FIND_ALL = "select * from users";
    private static final String SQL_UPDATE_USER_BY_ID = "update users set username = ? where id = ?";
    private static final String SQL_DELETE_BY_ID = "delete from films where id = ?";

    @Override
    public void create(String username) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, parseId());
            preparedStatement.setString(2, username);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
        }
    }
    @Override
    public User findById(String id) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String uuid = resultSet.getString("id");
                    String username = resultSet.getString("username");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    return new User(uuid, username,createdAt);
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
    public User findByUsername(String username) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String uuid = resultSet.getString("id");
                    String name = resultSet.getString("username");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    return new User(uuid, username,createdAt);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<User> showAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String uuid = resultSet.getString("id");
                    String name = resultSet.getString("username");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    userList.add(new User(uuid, name, createdAt));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public User update(String id, String username) {
        User user = findById(id);

        if (user != null) {
            try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_UPDATE_USER_BY_ID)) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, id);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows <= 0) {
                    throw new SQLException("Ошибка при редактировании пользователя.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Пользователь не найден");
        }
        User updatedUser = findById(id);
        return updatedUser;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteByUsername(String username) {

    }

    private String parseId() {
        String uuid;
        uuid = UUID.randomUUID()
                .toString()
                .substring(0, length);
        return uuid;
    }
}