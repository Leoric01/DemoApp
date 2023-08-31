package com.bank.mysuperbank.models;

import jakarta.persistence.Entity;

@Entity
public class Users {

  @jakarta.persistence.Id private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
