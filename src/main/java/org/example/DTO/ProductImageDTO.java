package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.Product;
import org.example.model.ProductImage;

@Data
@NoArgsConstructor
public class ProductImageDTO {
    private Long productImageId;
    private Long productId;
    private String picture;

    public ProductImageDTO(@NonNull ProductImage productImage){
        this.productImageId = productImage.getProductImageId();
        this.productId = productImage.getProduct().getProductId();
        this.picture = productImage.getPicture();
    }
}
