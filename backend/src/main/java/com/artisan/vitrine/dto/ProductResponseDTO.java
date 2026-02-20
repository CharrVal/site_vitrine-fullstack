package com.artisan.vitrine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;
    private String thumbnail;

    private Long categoryId;
    private String categoryName;

    private List<ProductImageDTO> images = new ArrayList<>();
}

