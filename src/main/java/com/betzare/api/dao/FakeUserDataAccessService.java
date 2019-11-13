package com.betzare.api.dao;

import com.betzare.api.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDAO {

  private static List<User> DB = new ArrayList<>();

  @Override
  public int addUser(UUID id, User user) {
    DB.add(new User(id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getIsConfirmed()));
    return 1;
  }

  @Override
  public Optional<User> getUser(UUID id) {
    return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
  }

  @Override
  public List<User> getUsers() {
    return DB;
  }

  @Override
  public int updateUser(UUID id, User userToUpdate) {
    return getUser(id).map(user -> {
      int indexOfUserToUpdate = DB.indexOf(user);
      if (indexOfUserToUpdate >= 0) {
        DB.set(indexOfUserToUpdate, new User(id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getIsConfirmed()));
        return 1;
      }
      return 0;
    }).orElse(0);
  }

  @Override
  public int deleteUser(UUID id) {
    Optional<User> personOptional = getUser(id);
    if (personOptional.isEmpty()) {
      return 0;
    }

    DB.remove(personOptional.get());
    return 1;
  }
}
