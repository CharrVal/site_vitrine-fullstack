package com.artisan.vitrine.Persistence.repository;

import com.artisan.vitrine.Persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
