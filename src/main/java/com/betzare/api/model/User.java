package com.betzare.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

  private final UUID id;
  @NotBlank
  private final String firstName;
  @NotBlank
  private final String lastName;
  @NotBlank
  private final String email;
  @NotBlank
  private final String password;
  @NotBlank
  private final String isConfirmed;


  public User(@JsonProperty("id") UUID id, @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName, @JsonProperty("email") String email,
      @JsonProperty("password") String password, @JsonProperty("isConfirmed") String isConfirmed) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.isConfirmed = isConfirmed;
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getIsConfirmed() {
    return isConfirmed;
  }
}
