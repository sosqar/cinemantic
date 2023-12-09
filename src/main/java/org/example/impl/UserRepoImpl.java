package org.example.impl;

import org.example.config.ConnectionManager;
import org.example.config.MyId;
import org.example.model.User;
import org.example.service.UserRepo;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserRepoImpl implements UserRepo {
    private final Logger LOGGER = Logger.getLogger(UserRepoImpl.class.getName());
    private static final String SQL_SAVE = "insert into users (id, username, created_at) VALUES (?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "select * from users where id = ?";
    private static final String SQL_FIND_BY_USERNAME = "select * from users where username = ?";
    private static final String SQL_FIND_ALL = "select * from users";
    private static final String SQL_UPDATE_USER_BY_ID = "update users set username = ? where id = ?";
    private static final String SQL_DELETE_BY_ID = "delete from users where id = ?";
//    private static final String SQL_DELETE_BY_USERNAME = "delete from users where username = ?";

    @Override
    public User create(String username) {
        MyId uuid = new MyId();
        User user = findByUsername(username);
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, uuid.parse());
            preparedStatement.setString(2, username);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                String id = generatedKeys.getString(1);
                String name = generatedKeys.getString(2);
                Timestamp createdAt = generatedKeys.getTimestamp(3);
                return new User(id, username, createdAt);
            } catch (SQLException e) {
                LOGGER.warning("Ошибка при создании пользователя. Ни одна запись не была изменена.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Пользователь с таким именем существует. Попробуйте снова",e);
        }
        return null;
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
                    return new User(uuid, username, createdAt);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
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
                    return new User(uuid, username, createdAt);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
        }
        return null;
    }

    @Override
    public String getUserIdByUsername(String username) {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("id");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при обработке результата запроса", e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка при выполнении запроса", e);
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
            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
        }
        System.out.println("List of users: " + userList);
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
                    LOGGER.log(Level.SEVERE,"Ошибка при редактировании пользователя, попробуйте снова");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
            }
        } else {
            System.out.println("Пользователь не найден");
        }
        return findById(id);
    }

    @Override
    public User deleteByUsername(String username) {
        User deletedUser = findById(username);

        if (deletedUser != null) {
            try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_DELETE_BY_ID)) {

                preparedStatement.setString(1, username);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows <= 0) {
                    throw new SQLException("Ошибка при удалении пользователя. Ни одна запись не была изменена");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (deletedUser != null) {
            System.out.println("Пользователь успешно удален:");
            System.out.println("ID: " + deletedUser.getId());
            System.out.println("Username: " + deletedUser.getUsername());
        }
        return deletedUser;
    }
}