package org.example.service;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import org.example.DTO.InventoryDTO;
import org.example.DTO.OrderItemDTO;
import org.example.DTO.ProductDTO;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.Hibernate.map;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
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
}
