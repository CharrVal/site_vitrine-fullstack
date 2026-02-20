package com.artisan.vitrine.service;

import com.artisan.vitrine.dto.CategoryRequestDTO;
import com.artisan.vitrine.dto.CategoryResponseDTO;
import com.artisan.vitrine.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    CategoryResponseDTO findById(Long id);
    Category findCategoryById(Long Id);
    List<CategoryResponseDTO> findAllCategory();
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTOToUpdate);
    void deleteById(Long id);
}
