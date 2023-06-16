package org.example.service;

import org.example.DTO.OrderDTO;
import org.example.DTO.OrderItemDTO;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.repository.OrderItemsRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public List<OrderItemDTO> getOrderItems() {
        return orderItemsRepository.findAll().stream().map(OrderItemDTO::new).toList();
    }

    public void addOrderItem(OrderItemDTO orderItemDTO) {
        orderItemsRepository.save(new OrderItem(orderItemDTO, orderRepository.findById(orderItemDTO.getOrderId()).get(), productRepository.findById(orderItemDTO.getProductId()).get()));
    }

    public void saveAll(List<OrderItemDTO> orderItemDTOList) {
        orderItemsRepository.saveAll(orderItemDTOList.stream().map(orderItemDTO -> new OrderItem(orderItemDTO, orderRepository.findById(orderItemDTO.getOrderId()).get(), productRepository.findById(orderItemDTO.getProductId()).get())).toList());
    }

    public List<OrderItem> findOrderItems(OrderDTO orderDTO) {
        List<Long> orderItemIdList = orderDTO.getOrderItemsId();
        List<OrderItem> orderItemList = orderItemsRepository.findAll();
        List<Long> orderItemAllIdList = orderItemList.stream().map(OrderItem::getOrderItemId).toList();
        List<OrderItem> filteredOrderItems = new ArrayList<>();
//        orderItemList.stream().map(orderItem -> orderItem.getOrderItemId())
//        return stream().filter(orderItemIdList.contains(orderItemList.stream().map(orderItem -> orderItem.getOrderItemId())))
//        orderItemList.forEach(orderItem -> orderItem.getOrderItemId().equals(orderItemIdList.forEach(aLong -> aLong)));
        for (OrderItem orderItem : orderItemList) {
            if(orderItemIdList.contains(orderItem.getOrderItemId())) {
                filteredOrderItems.add(orderItem);
            }
        }
        return filteredOrderItems;
    }
}
