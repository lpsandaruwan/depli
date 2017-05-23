package com.depli.repository;

import com.depli.entity.Role;
import com.depli.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by lpsandaruwan on 5/23/17.
 */

public interface UserRepository extends Repository<User, Long> {

    // Get a list by user role
    List<User> findAllByRole(Role role);

    // Get user data by email
    User findByEmail(String email);

    // Get user by user Id
    User findByUserId(Long userId);

    // Get user data by username
    User findByUsername(String username);

    // Remove user by user Id
    User removeByUserId(Long userId);

    // Remove user by username
    User removeByUsername(String username);

    // Save user date
    User save(User user);
}
