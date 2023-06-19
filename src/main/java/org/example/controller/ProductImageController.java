package org.example.controller;

import org.example.DTO.ProductDTO;
import org.example.DTO.ProductImageDTO;
import org.example.repository.ProductImageRepository;
import org.example.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/productImages")
//    @CrossOrigin
    ResponseEntity<List<ProductImageDTO>> getProductImages() {
        List<ProductImageDTO> productImageDTOList = productImageService.getProductImages();

        return new ResponseEntity<List<ProductImageDTO>>(productImageDTOList, HttpStatus.OK);

    }

    @PostMapping("/productImage")
    void addProductImage(@RequestBody ProductImageDTO productImageDTO){
        productImageService.addProductImage(productImageDTO);
    }

    @PostMapping("/productImages")
    void addProductImages(@RequestBody List<ProductImageDTO> productImageDTOList){
        productImageService.saveAll(productImageDTOList);
    }
}
