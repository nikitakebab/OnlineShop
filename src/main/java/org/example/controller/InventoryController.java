package org.example.controller;

import org.example.DTO.InventoryDTO;
import org.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventories")
//    @CrossOrigin
    ResponseEntity<List<InventoryDTO>> getInventories() {
        List<InventoryDTO> inventories = inventoryService.getInventory();

        return new ResponseEntity<List<InventoryDTO>>(inventories, HttpStatus.OK);

    }

    @PostMapping("/inventory")
    void addInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryService.addInventory(inventoryDTO);
    }

    @PostMapping("/inventories")
    void addInventories(@RequestBody List<InventoryDTO> inventoryDTOList){
        inventoryService.saveAll(inventoryDTOList);
    }
}
