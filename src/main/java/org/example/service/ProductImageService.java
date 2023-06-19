package org.example.service;

import org.example.DTO.ProductDTO;
import org.example.DTO.ProductImageDTO;
import org.example.model.Product;
import org.example.model.ProductImage;
import org.example.repository.ProductImageRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductRepository productRepository;

    public List<ProductImageDTO> getProductImages(Long productImageId, Long productId) {
        return productImageRepository.findAll(Example.of(new ProductImage(
                productImageId,
                productRepository.findById(productId).get()
        ))).stream().map(ProductImageDTO::new).toList();
    }

    public void addProductImage(ProductImageDTO productImageDTO) {
        productImageRepository.save(new ProductImage(productImageDTO, productRepository.findById(productImageDTO.getProductId()).get()));
    }

    public void saveAll(List<ProductImageDTO> productImageDTOList) {
        productImageRepository.saveAll(productImageDTOList.stream().map(productImageDTO -> new ProductImage(productImageDTO, productRepository.findById(productImageDTO.getProductId()).get())).toList());
    }
}
