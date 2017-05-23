package com.depli.data.domain;

import com.depli.entity.Role;
import com.depli.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by lpsandaruwan on 5/23/17.
 */

public class CurrentUser extends org.springframework.security.core.userdetails.User{

    private User user;

    public CurrentUser(User user) {
        super(
                user.getUsername(),
                user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getRole().toString())
        );
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser {" +
                "user=" + user +
                "}" + super.toString();
    }
}
