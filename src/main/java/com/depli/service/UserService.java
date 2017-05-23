package com.depli.service;

import com.depli.entity.Role;
import com.depli.entity.User;
import com.depli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lpsandaruwan on 5/23/17.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get a list of users by user role
    public List<User> findByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    // Get user data by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get user by user Id
    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    // Get user data by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Remove user by user Id
    public User removeByUserId(Long userId) {
        return userRepository.removeByUserId(userId);
    }

    // Remove user by username
    public User removeByUsername(String username) {
        return userRepository.removeByUsername(username);
    }

    // Save user date
    public User save(User user) {
        return userRepository.save(user);
    }
}
