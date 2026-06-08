package com.artisan.vitrine.Persistence.repository;

import com.artisan.vitrine.Persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop10ByNameContainingIgnoreCase(String trim);
}
