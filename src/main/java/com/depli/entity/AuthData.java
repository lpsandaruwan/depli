package com.depli.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lpsandaruwan on 3/31/17.
 */

@Entity
@Table(name = "auth_data")
public class AuthData {

    @Id
    @Column(name="auth_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authId;

    @Column(name="username")
    @Size(max = 50)
    private String username;

    @Column(name="password")
    @Size(max = 50)
    private String password;

    protected AuthData() {}

    public AuthData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
