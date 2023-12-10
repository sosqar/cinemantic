//package org.example.impl;
//
//import org.example.config.ConnectionManager;
//import org.example.model.Film;
//import org.example.service.MovieRepository;
//
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class FilmRepoImpl implements MovieRepository {
//    private final Logger LOGGER = Logger.getLogger(MovieRepository.class.getName());
//    private static final String SQL_SAVE = "insert into films (title, genre, description, created_at) VALUES " +
//            "(?, ?, ?, ?)";
//    private static final String SQL_FIND_BY_TITLE = "select * from films where title =?";
//    private static final String SQL_FIND_BY_ID = "select * from films where id = ?";
//    private static final String SQL_DELETE_BY_ID = "delete from films where id = ?";
//    private static final String SQL_UPDATE_FILM_BY_ID = "update films set genre = ?, author = ?, description = ? " +
//            "where id = ?";
//
//
//    public Film create(Film film) {
//        try (Connection connection = ConnectionManager.getConnect();
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE,
//                     Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, film.getTitle());
//            preparedStatement.setString(2, film.getGenre());
//            preparedStatement.setString(3, film.getDescription());
//            preparedStatement.setObject(4, Timestamp.valueOf(LocalDateTime.now()));
//            int affectedRows = preparedStatement.executeUpdate();
//
//
//            if (affectedRows > 0) {
//                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
//                    if (resultSet.next()) {
//                        String id = resultSet.getString("id");
//                        String title = resultSet.getString("title");
//                        String genre = resultSet.getString("genre");
//                        String description = resultSet.getString("description");
//                        Timestamp created_at = resultSet.getTimestamp("created_at");
//                        return new Film(id, title, genre, description, created_at);
//                    } else {
//                        LOGGER.warning("Ошибка при получении полей фильма.");
//                    }
//                }
//            } else {
//                LOGGER.warning("Ошибка при создании фильма, ни одна запись не была изменена.");
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Произошла ошибка", e);
//        }
//        return null;
//    }
//
//    public Film findByTitle(String query) {
//        try (Connection connection = ConnectionManager.getConnect();
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TITLE)) {
//            preparedStatement.setString(1, query);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String title = resultSet.getString("title");
//                    String genre = resultSet.getString("genre");
//                    String author = resultSet.getString("author");
//                    return new Film();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    public Film findById(int query) {
//        try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
//                connection.prepareStatement(SQL_FIND_BY_ID)) {
//            preparedStatement.setInt(1, query);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String title = resultSet.getString("title");
//                    String genre = resultSet.getString("genre");
//                    String author = resultSet.getString("author");
//                    String description = resultSet.getString("description");
//                    return new Film(id, title, genre, author, description);
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    public String getFilmIdByTitle(String title) {
//        try (Connection connection = ConnectionManager.getConnect();
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TITLE)) {
//            preparedStatement.setString(1, title);
//
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getString("id");
//                } else {
//                    LOGGER.warning("Фильм с таким названием " + title + " не найден.");
//                }
//            } catch (SQLException e) {
//                LOGGER.log(Level.SEVERE, "Ошибка при обработке результата запроса", e);
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Произошла ошибка при выполнении запроса", e);
//        }
//        return null;
//    }
//
//    public Film deleteById(int query) {
//        Film deletedFilm = findById(query);
//
//        if (deletedFilm != null) {
//            try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
//                    connection.prepareStatement(SQL_DELETE_BY_ID)) {
//
//                preparedStatement.setInt(1, query);
//                int affectedRows = preparedStatement.executeUpdate();
//                if (affectedRows <= 0) {
//                    throw new SQLException("Ошибка при удалении фильма. Ни одна запись не была изменена");
//                }
//
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if (deletedFilm != null) {
//            System.out.println("Фильм успешно удален:");
//            System.out.println("ID: " + deletedFilm.getId());
//            System.out.println("Title: " + deletedFilm.getTitle());
//            System.out.println("Author: " + deletedFilm.getAuthor());
//        }
//        return deletedFilm;
//    }
//
//    public Film updateFilm(String newGenre, String newAuthor, String newDescription, int id) {
//        Film targetFilm = findById(id);
//
//        if (targetFilm != null) {
//            try (Connection connection = ConnectionManager.getConnect(); PreparedStatement preparedStatement =
//                    connection.prepareStatement(SQL_UPDATE_FILM_BY_ID)) {
//
//                preparedStatement.setString(1, newGenre);
//                preparedStatement.setString(2, newAuthor);
//                preparedStatement.setString(3, newDescription);
//                preparedStatement.setInt(4, id);
//
//                int affectedRows = preparedStatement.executeUpdate();
//
//                if (affectedRows <= 0) {
//                    throw new SQLException("Ошибка при редактировании фильма. Ни одна запись не была изменена");
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        Film updatedFilm = findById(id);
//        if (targetFilm != null) {
//            System.out.println("Фильм успешно отредактирован:");
//            System.out.println("ID: " + updatedFilm.getId());
//            System.out.println("Жанр был изменен с \"" + targetFilm.getGenre() + "\" на \"" + updatedFilm.getGenre() + "\"");
//            System.out.println("Автор был изменен с \"" + targetFilm.getAuthor() + "\" на \"" + updatedFilm.getAuthor() + "\"");
//            System.out.println("Описание было изменено с \"" + targetFilm.getDescription() + "\" на \"" + updatedFilm.getDescription() + "\"");
//
//        }
//        return updatedFilm;
//    }
//}
