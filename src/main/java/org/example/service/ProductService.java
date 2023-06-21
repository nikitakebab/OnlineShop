package org.example.service;

import org.example.DTO.InventoryDTO;
import org.example.DTO.OrderItemDTO;
import org.example.DTO.ProductDTO;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> getProducts(
            Long productId,
            String productName,
            String description,
            String brand,
            String category,
            String sortType,
            String sortOrder
    ) {
        if (sortType != null) return productRepository.findAll(Example.of(new Product(
                productId,
                productName,
                description,
                brand,
                category
        )), Sort.by(Sort.Direction.fromString(sortOrder), sortType)).stream().map(ProductDTO::new).toList();
        else {
            return productRepository.findAll(Example.of(new Product(
                    productId,
                    productName,
                    description,
                    brand,
                    category
            ))).stream().map(ProductDTO::new).toList();
        }
    }

    public void addProduct(ProductDTO productDTO) {
        productRepository.save(new Product(productDTO));
    }

    public void saveAll(List<ProductDTO> productDTOList) {
        productRepository.saveAll(productDTOList.stream().map(Product::new).toList());
    }

    public Product findProduct(InventoryDTO inventoryDTO){
//        return productRepository.findAll().stream().filter(product -> product.getProductId().equals(inventoryDTO.getProductId())).toList().get(0);
        return productRepository.getById(inventoryDTO.getProductId());
    }

    public  Product findProduct(OrderItemDTO orderItemDTO){
//        return productRepository.findAll().stream().filter(product -> product.getProductId().equals(orderItemDTO.getProductId())).toList().get(0);
        return productRepository.getById(orderItemDTO.getProductId());
    }

    public void deleteProducts(Long productId, String productName, String description, String brand, String category) {
//        productRepository.delete(Example.of(new Product(productId, productName, description, brand, category)).getProbe());
        List<ProductDTO> productDTOList = productRepository.findAll(Example.of(new Product(
                productId,
                productName,
                description,
                brand,
                category
        ))).stream().map(ProductDTO::new).toList();
        productRepository.deleteAllById(productDTOList.stream().map(ProductDTO::getProductId).toList());
    }
}
