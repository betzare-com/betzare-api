package com.betzare.api.api;

import com.betzare.api.model.User;
import com.betzare.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public void addUser(@Valid @NotNull @RequestBody User user) {
    userService.createUser(user);
  }

  @GetMapping(path = "{id}")
  public User getUser(@PathVariable("id") UUID id) {
    return userService.getUser(id).orElse(null);
  }

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PutMapping(path = "{id}")
  public void updateUser(@PathVariable("id") UUID id,
      @Valid @NotNull @RequestBody User user) {
    userService.updateUser(id, user);
  }

  @DeleteMapping(path = "{id}")
  public void deleteUser(@PathVariable("id") UUID id) {
    userService.deleteUser(id);
  }
}
