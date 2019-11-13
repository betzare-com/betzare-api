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
  public int addUser(UUID id, User user) {
    String sql = "INSERT INTO users (id, is_confirmed, first_name, last_name, email, password) VALUES (?, ?, ?, ?, ?, ?)";

    return jdbcTemplate.update(sql, id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
  }

  @Override
  public Optional<User> getUser(UUID id) {
    String sql = "SELECT id, is_confirmed, first_name, last_name, email FROM users WHERE id = ?";

    User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
      UUID userId = UUID.fromString(resultSet.getString("id"));
      String firstName = resultSet.getString("firstName");
      String lastName = resultSet.getString("lastName");
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      String isConfirmed = resultSet.getString("isConfirmed");
      return new User(userId, firstName, lastName, email, password, isConfirmed);
    });

    return Optional.ofNullable(user);
  }

  @Override
  public List<User> getUsers() {
    final String sql = "SELECT id, is_confirmed, first_name, last_name, email FROM users";
    return jdbcTemplate.query(sql, (resultSet, i) -> {
      UUID id = UUID.fromString(resultSet.getString("id"));
      String firstName = resultSet.getString("firstName");
      String lastName = resultSet.getString("lastName");
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      String isConfirmed = resultSet.getString("isConfirmed");
      return new User(id, firstName, lastName, email, password, isConfirmed);
    });
  }

  @Override
  public int updateUser(UUID id, User user) {
    String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, is_confirmed = ? WHERE id = ?";

    return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getIsConfirmed(), id);
  }

  @Override
  public int deleteUser(UUID id) {
    String sql = "DELETE FROM users WHERE id = ?";

    return jdbcTemplate.update(sql, id);
  }
}
