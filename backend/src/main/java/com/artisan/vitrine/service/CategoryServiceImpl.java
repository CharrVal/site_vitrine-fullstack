package com.artisan.vitrine.service;

import com.artisan.vitrine.dto.CategoryRequestDTO;
import com.artisan.vitrine.dto.CategoryResponseDTO;
import com.artisan.vitrine.entity.Category;
import com.artisan.vitrine.mapper.CategoryMapper;
import com.artisan.vitrine.repository.CategoryRepository;
import com.artisan.vitrine.service.exception.CategoryServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = mapper.toEntity(dto);
        Category saved = repository.save(category);
        return mapper.toResponse(saved);
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryServiceException("Category not found: " + id));
        return mapper.toResponse(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryServiceException("Category not found: " + id));
    }


    @Override
    public List<CategoryResponseDTO> findAllCategory() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category categoryExisting = repository.findById(id)
                .orElseThrow(() -> new CategoryServiceException("Category not found: " + id));
        categoryExisting.setName(dto.getName());
        Category saved = repository.save(categoryExisting);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryServiceException("Category not found: " + id));
        repository.delete(category);
    }
}
