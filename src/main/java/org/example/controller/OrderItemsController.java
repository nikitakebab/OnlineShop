package org.example.controller;

import org.example.DTO.OrderItemDTO;
import org.example.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderItemsController {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/orderItems")
//    @CrossOrigin
    ResponseEntity<List<OrderItemDTO>> getOrderItems() {
        List<OrderItemDTO> orderItemDTOList = orderItemService.getOrderItems();

        return new ResponseEntity<List<OrderItemDTO>>(orderItemDTOList, HttpStatus.OK);

    }

    @PostMapping("/orderItem")
    void addOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        orderItemService.addOrderItem(orderItemDTO);
    }

    @PostMapping("/orderItems")
    void addOrderItems(@RequestBody List<OrderItemDTO> orderItemDTOList){
        orderItemService.saveAll(orderItemDTOList);
    }
}
