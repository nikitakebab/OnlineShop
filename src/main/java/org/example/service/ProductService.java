package org.example.service;

import org.example.DTO.InventoryDTO;
import org.example.DTO.OrderItemDTO;
import org.example.DTO.ProductDTO;
import org.example.model.Inventory;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<ProductDTO> getProducts(
            Long productId,
            String productName,
            String description,
            String brand,
            String category,
            String sortType,
            String sortOrder,
            int pageNum,
            int pageSize
    ) {

        List<ProductDTO> productDTOList;
        Pageable pageable;
        List<ProductDTO> allProducts;
        if (sortType != null) {
            pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(sortOrder), sortType));
            allProducts = productRepository.findAll(Example.of(new Product(
                    productId,
                    productName,
                    description,
                    brand,
                    category
            )), Sort.by(Sort.Direction.fromString(sortOrder), sortType)).stream().map(ProductDTO::new).toList();
        }
        else {
            pageable = PageRequest.of(pageNum, pageSize);
            allProducts = productRepository.findAll(Example.of(new Product(
                    productId,
                    productName,
                    description,
                    brand,
                    category
            ))).stream().map(ProductDTO::new).toList();
        }
        productDTOList = productRepository.findAll(Example.of(new Product(
                productId,
                productName,
                description,
                brand,
                category
        )), pageable).stream().map(ProductDTO::new).toList();
        return new PageImpl<>(productDTOList, pageable, allProducts.size());
    }

    public List<String> getBrands() {
//        List<Long> availableProductIds = inventoryRepository.findAll().stream()
//                .filter(inventory -> inventory.getQuantity() > 0)
//                .map(Inventory::getInventoryId).toList();

//        inventoryRepository.getAvailableProductIds();

//        return productRepository.findAll().stream().map(ProductDTO::new).map(ProductDTO::getBrand).toList();
//        return  productRepository.findAll().stream().map(Product::getBrand).toList();
//        List<Long> productIds = productRepository.findAll().stream().map(Product::getProductId).toList();
        return productRepository.getAvailableBrands();
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
