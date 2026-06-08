package com.artisan.vitrine.Business.service;

import com.artisan.vitrine.Presentation.dto.ProductRequestDTO;
import com.artisan.vitrine.Presentation.dto.ProductResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO dto, List<MultipartFile> images) throws IOException;
    ProductResponseDTO findById(Long id);
    List<ProductResponseDTO> findAllProduct();
    ProductResponseDTO updateProduct(Long id, String productRequestDtoJson, List<MultipartFile> images, List<Long> deletedImageIds) throws IOException;
    List<ProductResponseDTO> searchProducts(String search);
    void deleteById(Long id);
    List<ProductResponseDTO> findByCategoryId(Long id);
}
