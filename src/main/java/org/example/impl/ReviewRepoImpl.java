package org.example.impl;

import com.github.javafaker.Faker;
import org.example.config.ConnectionManager;
import org.example.config.MyId;
import org.example.model.Review;
import org.example.service.Randomizer;
import org.example.service.ReviewRepo;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewRepoImpl implements ReviewRepo {
    Faker faker = new Faker();
    Randomizer random = new Randomizer();
    private final Logger LOGGER = Logger.getLogger(ReviewRepo.class.getName());
    private static final String SQL_SAVE = "insert into reviews (id, user_id, film_id, value , description, " +
            "created_at) " +
            "VALUES " +
            "(?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_NAME = "select * from genres where name = ?";
    private static final String SQL_DELETE_BY_ID = "delete from genres where id = ?";


    @Override
    public Review create(String user, String title, int value, String description) {
        MyId uuid = new MyId();
        UserRepoImpl uri = new UserRepoImpl();
        FilmRepoImpl fri = new FilmRepoImpl();

        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, uuid.parse());
            preparedStatement.setString(2, uri.getUserIdByUsername(user));
            preparedStatement.setString(3, fri.getFilmIdByTitle(title));
            preparedStatement.setInt(4, value);
            preparedStatement.setString(5, description);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        String id = generatedKeys.getString(1);
                        String user_id = uri.getUserIdByUsername(user); // Используем ранее полученный ID пользователя
                        String film_id = fri.getFilmIdByTitle(title); // Используем ранее полученный ID фильма
                        Timestamp createdAt = generatedKeys.getTimestamp(4); // Изменил индекс для "created_at"
                        return new Review(id, user_id, film_id, value, description, createdAt);
                    }
                } catch (SQLException e) {
                    LOGGER.warning("Ошибка при получении сгенерированных ключей.");
                }
            }  else{
                LOGGER.warning("Ошибка при создании отзыва.");
            }
        } catch(SQLException e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка при выполнении запроса.", e);
        }
        return null;
    }


    @Override
    public Review update(String id, String username) {
        return null;
    }

    @Override
    public Review findById(String id) {
        return null;
    }

    @Override
    public Review deleteById(String id) {
        return null;
    }

    @Override
    public List<Review> showAll() {
        return null;
    }
}
