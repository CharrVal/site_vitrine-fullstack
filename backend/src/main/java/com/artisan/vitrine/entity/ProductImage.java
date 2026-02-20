package com.artisan.vitrine.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="productImages")
@NoArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(Product product, String imagePath) {
        this.product = product;
        this.imagePath = imagePath;
    }

}
