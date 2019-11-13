package com.betzare.api.service;

import com.betzare.api.dao.UserDAO;
import com.betzare.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

  private final UserDAO userDAO;

  @Autowired
  public UserService(@Qualifier("postgres") UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public int addUser(User user) {
    return userDAO.addUser(user);
  }

  public Optional<User> getUser(UUID id) {
    return userDAO.getUser(id);
  }

  public List<User> getUsers() {
    return userDAO.getUsers();
  }

  public int updateUser(UUID id, User userToBeUpdated) {
    return userDAO.updateUser(id, userToBeUpdated);
  }

  public int deleteUser(UUID id) {
    return userDAO.deleteUser(id);
  }

}
