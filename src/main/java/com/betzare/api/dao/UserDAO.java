package com.betzare.api.dao;

import com.betzare.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {

  int createUser(UUID id, User user);

  default int createUser(User user) {
    UUID id = UUID.randomUUID();
    return createUser(id, user);
  }

  Optional<User> getUser(UUID id);

  List<User> getUsers();

  int updateFirstName(UUID id, String firstName);

  int updateLastName(UUID id, String lastName);

  int updateEmail(UUID id, String email);

  int updateIsConfirmed(UUID id, Boolean isConfirmed);

  int deleteUser(UUID id);
}
