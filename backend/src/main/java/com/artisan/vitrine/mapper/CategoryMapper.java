package com.artisan.vitrine.mapper;

import com.artisan.vitrine.dto.CategoryRequestDTO;
import com.artisan.vitrine.dto.CategoryResponseDTO;
import com.artisan.vitrine.entity.Category;
import com.artisan.vitrine.entity.Product;
import com.artisan.vitrine.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public CategoryMapper(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());

        if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {
            List<Product> products = dto.getProductIds().stream()
                    .map(id -> productRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Product not found: " + id)))
                    .toList();
            category.setProducts(products);
        }

        return category;
    }

    public CategoryResponseDTO toResponse(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getProducts() != null) {
            dto.setProducts(
                    category.getProducts().stream()
                            .map(productMapper::toListDTO)
                            .toList()
            );
        }

        return dto;
    }
}

