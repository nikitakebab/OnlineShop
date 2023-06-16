package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDTO {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;

    public OrderItemDTO(@NonNull OrderItem orderItem){
        this.orderItemId = orderItem.getOrderItemId();
        this.orderId = orderItem.getOrder().getOrderId();
        this.productId = orderItem.getProduct().getProductId();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
