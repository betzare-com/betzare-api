package com.betzare.api.dao;

import com.betzare.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {

  int addUser(UUID id, User user);

  default int addUser(User user) {
    UUID id = UUID.randomUUID();
    return addUser(id, user);
  }

  Optional<User> getUser(UUID id);

  List<User> getUsers();

  int updateUser(UUID id, User user);

  int deleteUser(UUID id);
}
