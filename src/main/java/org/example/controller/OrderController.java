package org.example.controller;

import org.example.DTO.OrderDTO;
import org.example.DTO.ProductDTO;
import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    ResponseEntity<List<OrderDTO>> getOrders() {
        List<OrderDTO> orders = orderService.getOrders();

        return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);

    }

    @PostMapping("/order")
    void addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
    }

    @PostMapping("/orders")
    void addOrders(@RequestBody List<OrderDTO> orderDTOList){
        orderService.saveAll(orderDTOList);
    }
}
