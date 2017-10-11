package com.depli.security.controller.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class InputUser implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;

  private String password;

  private String firstname;

  private String lastname;

  private String email;

  private boolean enabled;

  private Date lastPasswordResetDate;

  private List<String> authorities;

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setLastPasswordResetDate(Date lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
  }

  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  public void setAuthorities(List<String> authorities) {
    this.authorities = authorities;
  }

  public List<String> getAuthorities() {
    return authorities;
  }
}