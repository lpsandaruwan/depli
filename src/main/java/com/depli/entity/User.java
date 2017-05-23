package com.depli.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lpsandaruwan on 5/23/17.
 */

@Entity
@Table(name = "users")
public class User {

    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Column(name = "email")
    @Size(max = 100)
    private String email;

    @Column(name = "name")
    @Size(max = 30)
    private String name;

    @Column(name = "password_hash")
    @NotNull
    private String passwordHash;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Column(name = "username", unique = true)
    @NotNull
    @Size(max = 15)
    private String username;

    protected User() {}

    public User(String email,
                String name,
                String passwordHash,
                Role role,
                String username) {
        this.email = email;
        this.name = name;
        this.passwordHash = passwordHash;
        this.role = role;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
