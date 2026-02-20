package com.artisan.vitrine.controller;

import com.artisan.vitrine.dto.CategoryRequestDTO;
import com.artisan.vitrine.dto.CategoryResponseDTO;
import com.artisan.vitrine.dto.ProductResponseDTO;
import com.artisan.vitrine.service.CategoryService;
import com.artisan.vitrine.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;
    private final ProductService productService;

    public CategoryController(CategoryService service,  ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAllCategory());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable Long id) {
        List<ProductResponseDTO> products = productService.findByCategoryId(id);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO dto) {
        return ResponseEntity.ok(service.createCategory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO categoryExisting = service.updateCategory(id, dto);
        return ResponseEntity.ok(categoryExisting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
