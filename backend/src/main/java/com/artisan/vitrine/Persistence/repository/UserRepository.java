package com.artisan.vitrine.Persistence.repository;

import com.artisan.vitrine.Persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Long id);
    Optional<User> findByUsername(@Param("username")String username);
    void deleteUserById(Long id);
}
