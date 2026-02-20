package com.artisan.vitrine.mapper;

import com.artisan.vitrine.dto.ProductRequestDTO;
import com.artisan.vitrine.dto.ProductResponseDTO;
import com.artisan.vitrine.entity.Category;
import com.artisan.vitrine.entity.Product;
import com.artisan.vitrine.dto.ProductImageDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        return product;
    }

    public ProductResponseDTO toResponse(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getImages() != null) {
            List<ProductImageDTO> imageDTOs = product.getImages().stream()
                    .map(img -> {
                        ProductImageDTO imgDTO = new ProductImageDTO();
                        imgDTO.setId(img.getId());
                        imgDTO.setImagePath(img.getImagePath());
                        return imgDTO;
                    })
                    .toList();
            dto.setImages(imageDTOs);
        }

        return dto;
    }

    public ProductResponseDTO toListDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            dto.setThumbnail(product.getImages().get(0).getImagePath());
        }
        return dto;
    }
}

