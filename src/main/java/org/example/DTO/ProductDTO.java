package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.Product;

@Data
@NoArgsConstructor
public class ProductDTO {
    public ProductDTO(@NonNull Product product) {
        this.productId = product.getProductId();
        this.brand = product.getBrand();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    private Long productId;
    private String productName;
    private String description;
    private double price;
    private String brand;
    private String category;
}
