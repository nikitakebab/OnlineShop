package org.example.controller;

import org.example.DTO.OrderDTO;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
//    @CrossOrigin
    ResponseEntity<List<OrderDTO>> getOrders(@RequestParam(value = "order_id", required = false) Long orderId,
                                             @RequestParam(value = "customer_id", required = false) Long customerId
    ) {
        List<OrderDTO> orders = orderService.getOrders(orderId, customerId);

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
