package com.artisan.vitrine.dto;

import com.artisan.vitrine.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<ProductResponseDTO> products;
}
