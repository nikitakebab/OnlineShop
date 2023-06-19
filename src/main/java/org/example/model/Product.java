package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.DTO.ProductDTO;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "brand")
    private String brand;
    @Column(name = "category")
    private String category;

    public Product(@NonNull ProductDTO productDTO) {
        this.productId = productDTO.getProductId();
        this.productName = productDTO.getProductName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.brand = productDTO.getBrand();
        this.category = productDTO.getCategory();
    }

    public Product(
            Long productId,
            String productName,
            String description,
            String brand,
            String category
    ) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.brand = brand;
        this.category = category;
    }
}
