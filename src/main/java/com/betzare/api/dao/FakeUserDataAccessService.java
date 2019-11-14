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
  public int createUser(UUID id, User user) {
    DB.add(new User(id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getGender(), user.getIsConfirmed()));
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
  public int updateFirstName(UUID id, String firstName) {
    return 0;
  }

  @Override
  public int updateLastName(UUID id, String lastName) {
    return 0;
  }

  @Override
  public int updateEmail(UUID id, String email) {
    return 0;
  }

  @Override
  public int updateIsConfirmed(UUID id, Boolean isConfirmed) {
    return 0;
  }

  // @override
  // public int updateuser(uuid id, user usertoupdate) {
  //  return getuser(id).map(user -> {
  //    int indexofusertoupdate = db.indexof(user);
  //    if (indexofusertoupdate >= 0) {
  //      db.set(indexofusertoupdate, new user(id, user.getfirstname(), user.getlastname(), user.getemail(), user.getpassword(), user.getisconfirmed()));
  //      return 1;
  //    }
  //    return 0;
  //  }).orelse(0);
  //}

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
