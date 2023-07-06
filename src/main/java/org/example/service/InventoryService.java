package org.example.service;

import org.example.DTO.InventoryDTO;
import org.example.DTO.ProductDTO;
import org.example.model.Inventory;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<InventoryDTO> getInventory(Long inventoryId, Long productId) {
        return inventoryRepository.findAll(Example.of(new Inventory(
                inventoryId,
                productRepository.findById(productId).get()
        ))).stream().map(InventoryDTO::new).toList();
    }

    public HashSet<Double> getSizesByBrands(List<String> brands) {
        if(brands!= null) return new HashSet<>(inventoryRepository.getSizesByBrands(brands));
        else return new HashSet<>(inventoryRepository.getAllSizes());
    }

    public void addInventory(InventoryDTO inventoryDTO) {
        inventoryRepository.save(new Inventory(
                inventoryDTO,
                productRepository.findById(inventoryDTO.getProductId()).get())
        );
    }

    public void saveAll(List<InventoryDTO> inventoryDTOList) {
        inventoryRepository.saveAll(inventoryDTOList.stream()
                .map(inventoryDTO -> new Inventory(
                        inventoryDTO,
                        productRepository.findById(inventoryDTO.getProductId()).get())
                ).toList());
    }
}
