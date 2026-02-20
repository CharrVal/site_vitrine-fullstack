package com.artisan.vitrine.repository;

import com.artisan.vitrine.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByLibelle(String roleAdmin);
}
