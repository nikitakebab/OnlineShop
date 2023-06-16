package org.example.controller;

import org.example.DTO.ProductDTO;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.getProducts();

        return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);

    }

//    @GetMapping("/penises")
//    void createTestData() {
//        Product product1 = new Product(1L, "Small Penis", "small penis", 50);
//        Product product2 = new Product(2L, "Big Penis", "real big penis", 100);
//        productService.saveAll(Arrays.asList(product1, product2));
//    }
    //use Postman to create test data

    @PostMapping("/product")
    void addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
    }

    @PostMapping("/products")
    void addProducts(@RequestBody List<ProductDTO> productDTOList){
        productService.saveAll(productDTOList);
    }
}
