package org.example.controller;

import org.example.DAO.ProductDAO;
import org.example.DTO.ProductDTO;
import org.example.repository.InventoryRepository;
import org.example.service.InventoryService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDAO productDAO;

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/products")
//    @CrossOrigin
    ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(value = "product_id", required = false) Long productId,
            @RequestParam(value = "product_name", required = false) String productName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "brand", required = false) List<String> brands,
            @RequestParam(value = "size", required = false) List<Double> sizes,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "sortBy", required = false) String sortType,
            @RequestParam(value = "sortOrder", required = false) String sortOrder,
            @RequestParam(value = "page", required = true) int pageNum,
            @RequestParam(value = "page_size", required = true) int pageSize
    ) {
        Page<ProductDTO> products = productDAO.getProducts(
                productId,
                productName,
                description,
                brands,
                sizes,
                category,
                sortType,
                sortOrder,
                pageNum,
                pageSize
        );

        return new ResponseEntity<Page<ProductDTO>>(products, HttpStatus.OK);

    }

    @GetMapping("/products/brands")
    ResponseEntity<HashSet<String>> getBrands() {
        HashSet<String> brands = productService.getBrands();
        return  new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/products/sizes")
    ResponseEntity<HashSet<Double>> getSizes(
            @RequestParam(value = "brands", required = false) List<String> brands
    ) {
//        HashSet<Double> sizes = productDAO.getSizes(brands);
        HashSet<Double> sizes = inventoryService.getSizesByBrands(brands);
        return  new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/products/{id}/sizes")
    @ResponseBody
    ResponseEntity<HashSet<Double>> getProductSizes(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductSizes(id), HttpStatus.OK);
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
