package com.artisan.vitrine.repository;

import com.artisan.vitrine.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop10ByNameContainingIgnoreCase(String trim);
}
