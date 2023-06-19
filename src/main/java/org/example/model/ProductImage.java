package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.DTO.ProductImageDTO;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long productImageId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column
    private String picture;

    public ProductImage(@NonNull ProductImageDTO productImageDTO, @NonNull Product product){
        this.productImageId = productImageDTO.getProductImageId();
        this.product = product;
        this.picture = productImageDTO.getPicture();
    }

    public ProductImage(Long productImageId, Product product) {
        this.productImageId = productImageId;
        this.product = product;
    }
}
