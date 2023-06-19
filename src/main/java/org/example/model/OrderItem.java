package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.DTO.OrderItemDTO;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;
//    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

//    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    public OrderItem(@NonNull OrderItemDTO orderItemDTO, @NonNull Order order, @NonNull Product product){
        this.orderItemId = orderItemDTO.getOrderItemId();
        this.order = order;
        this.product = product;
        this.quantity = orderItemDTO.getQuantity();
        this.price = orderItemDTO.getPrice();
    }
}
