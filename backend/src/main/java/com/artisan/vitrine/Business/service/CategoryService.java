package com.artisan.vitrine.Business.service;

import com.artisan.vitrine.Presentation.dto.CategoryRequestDTO;
import com.artisan.vitrine.Presentation.dto.CategoryResponseDTO;
import com.artisan.vitrine.Persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    CategoryResponseDTO findById(Long id);
    Category findCategoryById(Long Id);
    List<CategoryResponseDTO> findAllCategory();
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTOToUpdate);
    void deleteById(Long id);
}
