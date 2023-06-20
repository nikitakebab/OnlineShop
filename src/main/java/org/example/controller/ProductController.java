package org.example.controller;

import org.example.DTO.ProductDTO;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
//    @CrossOrigin
    ResponseEntity<List<ProductDTO>> getProducts(
            @RequestParam(value = "product_id", required = false) Long productId,
            @RequestParam(value = "product_name", required = false) String productName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "category", required = false) String category
    ) {
        List<ProductDTO> products = productService.getProducts(
                productId,
                productName,
                description,
                brand,
                category
        );

        return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);

    }

    @PostMapping("/product")
    void addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
    }

    @PostMapping("/products")
    void addProducts(@RequestBody List<ProductDTO> productDTOList){
        productService.saveAll(productDTOList);
    }

    @DeleteMapping("/products")
    void deleteProducts(
            @RequestParam(value = "product_id", required = false) Long productId,
            @RequestParam(value = "product_name", required = false) String productName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "category", required = false) String category
    ) {
        productService.deleteProducts(
                productId,
                productName,
                description,
                brand,
                category
        );
    }
}
