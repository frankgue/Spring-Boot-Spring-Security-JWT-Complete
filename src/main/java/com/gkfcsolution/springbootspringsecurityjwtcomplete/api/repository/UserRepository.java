package com.gkfcsolution.springbootspringsecurityjwtcomplete.api.repository;

import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created on 2025 at 19:24
 * File: null.java
 * Project: Spring-Boot-Spring-Security-JWT-Complete
 *
 * @author Frank GUEKENG
 * @date 21/09/2025
 * @time 19:24
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String usename);
    Optional<User> findByEmail(String email);
}
