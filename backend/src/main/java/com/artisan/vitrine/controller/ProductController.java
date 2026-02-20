package com.artisan.vitrine.controller;

import com.artisan.vitrine.dto.ProductRequestDTO;
import com.artisan.vitrine.dto.ProductResponseDTO;
import com.artisan.vitrine.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) throws IOException {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam String search) {
        List<ProductResponseDTO> results = service.searchProducts(search);
        return ResponseEntity.ok(results);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestPart("product") @Valid ProductRequestDTO dto, @RequestPart("images") List<MultipartFile> images) throws IOException {
        ProductResponseDTO created = service.createProduct(dto, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestPart("dto") String productRequestDtoJson,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "deletedImageIds", required = false) List<Long> deletedImageIds
    ) throws IOException {

        ProductResponseDTO response =
                service.updateProduct(id, productRequestDtoJson, images, deletedImageIds);

        return ResponseEntity.ok(response);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
