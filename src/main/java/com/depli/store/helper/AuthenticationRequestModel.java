package com.depli.store.helper;

import java.io.Serializable;

public class AuthenticationRequestModel implements Serializable {

  private static final long serialVersionUID = -8445943548965154778L;

  private String username;
  private String password;

  public AuthenticationRequestModel() {
    super();
  }

  public AuthenticationRequestModel(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}