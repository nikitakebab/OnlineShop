package org.example.service;

import org.example.DTO.OrderDTO;
import org.example.model.Order;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderItemsRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    public List<OrderDTO> getOrders(Long orderId, Long customerId) {
        return orderRepository.findAll(Example.of(new Order(
                orderId, customerRepository.findById(customerId).get()
        ))).stream().map(OrderDTO::new).toList();
    }

    public void addOrder(OrderDTO orderDTO) {
        orderRepository.save(new Order(
                orderDTO,
                customerRepository.findById(orderDTO.getCustomerId()).get(),
                orderItemsRepository.findAllById(orderDTO.getOrderItemsId())
        ));
    }

    public void saveAll(List<OrderDTO> orderDTOList) {
        orderRepository.saveAll(orderDTOList.stream().map(orderDTO -> new Order(
                orderDTO,
                customerRepository.findById(orderDTO.getCustomerId()).get(),
                orderItemsRepository.findAllById(orderDTO.getOrderItemsId())
        )).toList());
    }
}
