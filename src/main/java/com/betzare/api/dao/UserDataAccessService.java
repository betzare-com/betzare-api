package com.betzare.api.dao;

import com.betzare.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int createUser(UUID id, User user) {
    String sql = "INSERT INTO users (id, first_name, last_name, email, password, gender) VALUES (?, ?, ?, ?, ?, ?)";

    return jdbcTemplate.update(sql, id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getGender().name().toUpperCase());
  }

  @Override
  public Optional<User> getUser(UUID id) {
    String sql = "SELECT id, first_name, last_name, email, password, gender, is_confirmed FROM users WHERE id = ?";

    User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
      UUID userId = UUID.fromString(resultSet.getString("id"));
      String firstName = resultSet.getString("first_name");
      String lastName = resultSet.getString("last_name");
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      String genderStr = resultSet.getString("gender");
      User.Gender gender = User.Gender.valueOf(genderStr);
      Boolean isConfirmed = resultSet.getBoolean("is_confirmed");
      return new User(userId, firstName, lastName, email, password, gender, isConfirmed);
    });

    return Optional.ofNullable(user);
  }

  @Override
  public List<User> getUsers() {
    final String sql = "SELECT id, first_name, last_name, email, password, gender, is_confirmed FROM users";
    return jdbcTemplate.query(sql, (resultSet, i) -> {
      UUID id = UUID.fromString(resultSet.getString("id"));
      String firstName = resultSet.getString("first_name");
      String lastName = resultSet.getString("last_name");
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      String genderStr = resultSet.getString("gender");
      User.Gender gender = User.Gender.valueOf(genderStr);
      Boolean isConfirmed = resultSet.getBoolean("is_confirmed");
      return new User(id, firstName, lastName, email, password, gender, isConfirmed);
    });
  }

  @Override
  public int updateFirstName(UUID id, String firstName) {
    String sql = "UPDATE users SET first_name = ? WHERE id = ?";

    return jdbcTemplate.update(sql, firstName, id);
  }

  @Override
  public int updateLastName(UUID id, String lastName) {
    String sql = "UPDATE users SET last_name = ? WHERE id = ?";

    return jdbcTemplate.update(sql, lastName, id);
  }

  @Override
  public int updateEmail(UUID id, String email) {
    String sql = "UPDATE users SET email = ? WHERE id = ?";

    return jdbcTemplate.update(sql, email, id);
  }

  @Override
  public int updateIsConfirmed(UUID id, Boolean isConfirmed) {
    String sql = "UPDATE users SET is_confirmed = ? WHERE id = ?";

    return jdbcTemplate.update(sql, isConfirmed, id);
  }

  @Override
  public int deleteUser(UUID id) {
    String sql = "DELETE FROM users WHERE id = ?";

    return jdbcTemplate.update(sql, id);
  }
}
