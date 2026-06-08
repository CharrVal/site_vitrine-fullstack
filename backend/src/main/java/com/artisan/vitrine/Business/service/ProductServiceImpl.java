package com.artisan.vitrine.Business.service;

import com.artisan.vitrine.Persistence.entity.Category;
import com.artisan.vitrine.Persistence.entity.ProductImage;
import com.artisan.vitrine.Persistence.repository.CategoryRepository;
import com.artisan.vitrine.Business.service.exception.ProductServiceException;
import com.artisan.vitrine.Presentation.dto.ProductRequestDTO;
import com.artisan.vitrine.Presentation.dto.ProductResponseDTO;
import com.artisan.vitrine.Persistence.entity.Product;
import com.artisan.vitrine.Business.mapper.ProductMapper;
import com.artisan.vitrine.Persistence.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<ProductResponseDTO> searchProducts(String search) {
        if (search == null || search.trim().isEmpty()) {
            return Collections.emptyList();
        }
        List<Product> products = repository.findTop10ByNameContainingIgnoreCase(search.trim());
        return products.stream()
                .map(mapper:: toResponse)
                .collect(Collectors.toList());
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
