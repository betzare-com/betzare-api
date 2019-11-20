package com.betzare.api.service;

import com.betzare.api.dao.UserDAO;
import com.betzare.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.util.StringUtils;

@Service
public class UserService {

  private final UserDAO userDAO;

  @Autowired
  public UserService(@Qualifier("postgres") UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public int createUser(User user) {
    return userDAO.createUser(user);
  }

  public Optional<User> getUser(UUID id) {
    return userDAO.getUser(id);
  }

  public List<User> getUsers() {
    return userDAO.getUsers();
  }

  public void updateUser(UUID id, User user) {
    Optional.ofNullable(user.getFirstName()).filter(firstName -> !StringUtils.isEmpty(firstName))
        .map(StringUtils::capitalize).ifPresent(firstName -> {
      userDAO.updateFirstName(id, firstName);
    });

    Optional.ofNullable(user.getLastName()).filter(lastName -> !StringUtils.isEmpty(lastName))
        .map(StringUtils::capitalize).ifPresent(lastName -> {
      userDAO.updateLastName(id, lastName);
    });

    Optional.ofNullable(user.getEmail()).filter(email -> !StringUtils.isEmpty(email)).ifPresent(email -> {
      userDAO.updateEmail(id, email);
    });

    Optional.ofNullable(user.getIsConfirmed()).ifPresent(isConfirmed -> {
      userDAO.updateIsConfirmed(id, user.getIsConfirmed());
    });
  }

  public int deleteUser(UUID id) {
    return userDAO.deleteUser(id);
  }

}
