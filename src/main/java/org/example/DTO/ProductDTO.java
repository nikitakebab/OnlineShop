package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.Product;

import java.util.Date;

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
        this.createDate = product.getCreateDate();
    }

    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private String brand;
    private String category;
    private Date createDate;
}
