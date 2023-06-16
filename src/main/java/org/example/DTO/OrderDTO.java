package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.OrderItem;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Long customerId;
    private Date orderDate;
    private double totalAmount;
    private List<Long> orderItemsId;

    public OrderDTO(@NonNull Order order){
        this.orderId = order.getOrderId();
        this.customerId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.totalAmount = order.getTotalAmount();
        this.orderItemsId = order.getOrderItems().stream().map(OrderItem::getOrderItemId).toList();
    }
}
