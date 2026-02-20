package com.artisan.vitrine.service;

import com.artisan.vitrine.entity.Category;
import com.artisan.vitrine.entity.ProductImage;
import com.artisan.vitrine.repository.CategoryRepository;
import com.artisan.vitrine.service.exception.ProductServiceException;
import com.artisan.vitrine.dto.ProductRequestDTO;
import com.artisan.vitrine.dto.ProductResponseDTO;
import com.artisan.vitrine.entity.Product;
import com.artisan.vitrine.mapper.ProductMapper;
import com.artisan.vitrine.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository repository,
                              CategoryRepository categoryRepository,
                              ProductMapper mapper,
                              CategoryService categoryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto, List<MultipartFile> images) throws IOException {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow();

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);

        String uploadDir = "uploads/products/";
        Files.createDirectories(Paths.get(uploadDir));

        for (MultipartFile file : images) {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + filename);
            Files.copy(file.getInputStream(), path);

            ProductImage img = new ProductImage();
            img.setImagePath("products/" + filename);
            img.setProduct(product);

            product.getImages().add(img);
        }

        Product saved = repository.save(product);
        return mapper.toResponse(saved);
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Product not found with id " + id));
        return mapper.toResponse(product);
    }

    @Override
    public List<ProductResponseDTO> findAllProduct() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProductResponseDTO updateProduct(
            Long id,
            String productRequestDtoJson,
            List<MultipartFile> images,
            List<Long> deletedImageIds
    ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequestDTO dto =
                objectMapper.readValue(productRequestDtoJson, ProductRequestDTO.class);

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Product not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            Category category = categoryService.findCategoryById(dto.getCategoryId());
            product.setCategory(category);
        }

        if (deletedImageIds != null && !deletedImageIds.isEmpty()) {
            product.getImages().removeIf(img ->
                    deletedImageIds.contains(img.getId()));
        }

        if (images != null && !images.isEmpty()) {

            String uploadDir = "uploads/products/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir, filename);
                Files.copy(file.getInputStream(), path);

                ProductImage img = new ProductImage();
                img.setImagePath("products/" + filename);
                img.setProduct(product);

                product.getImages().add(img);
            }
        }

        Product saved = repository.save(product);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Product not found with id " + id));
        repository.delete(product);
    }

    @Override
    public List<ProductResponseDTO> findByCategoryId(Long id) {
        Category category = categoryService.findCategoryById(id);

        return category.getProducts().stream()
                .map(mapper::toResponse)
                .toList();
    }
}
