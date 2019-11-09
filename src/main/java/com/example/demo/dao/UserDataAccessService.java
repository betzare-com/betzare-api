package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDAO {

    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUser(UUID id, User user) {
        String sql = "INSERT INTO users (id, name) VALUES (?, ?)";

        return jdbcTemplate.update(sql, id, user.getName());
    }

    @Override
    public Optional<User> getUser(UUID id) {
        String sql = "SELECT id, name FROM users WHERE id = ?";

        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID userId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return  new User(userId, name);
        });

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getUsers() {
        final String sql = "SELECT id, name FROM users";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return  new User(id, name);
        });
    }

    @Override
    public int updateUser(UUID id, User user) {
        String sql = "UPDATE users SET name = ? WHERE id = ?";

        return jdbcTemplate.update(sql, user.getName(), id);
    }

    @Override
    public int deleteUser(UUID id) {
        String sql = "DELETE FROM users WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
}
