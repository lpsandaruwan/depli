package com.depli.store.persistent.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authority")
public class Authority {

  @Id
  @Column(name = "id")
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
//  @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", length = 50)
  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthorityName name;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private List<User> users;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Authority withId(Long id) {
    this.id = id;
    return this;
  }

  public AuthorityName getName() {
    return name;
  }

  public void setName(AuthorityName name) {
    this.name = name;
  }

  public Authority withName(AuthorityName name) {
    this.name = name;
    return this;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Authority withUsers(List<User> users) {
    this.users = users;
    return this;
  }
}