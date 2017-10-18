package com.depli.store.persistent.repository;

import com.depli.store.persistent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

  User save(User user);
}