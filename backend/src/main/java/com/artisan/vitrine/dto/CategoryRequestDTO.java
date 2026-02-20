package com.artisan.vitrine.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRequestDTO {
    @NotBlank(message = "Product name is required")

    private String name;
    private List<Long> productIds;

}
