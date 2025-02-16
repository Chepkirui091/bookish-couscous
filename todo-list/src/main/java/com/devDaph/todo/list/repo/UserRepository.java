package com.devDaph.todo.list.repo;

import com.devDaph.todo.list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String email, String password);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
