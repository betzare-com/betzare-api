package com.betzare.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

  private final UUID id;
  @NotBlank
  private final String name;

  public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public UUID getId() {
    return id;
  }
}
