package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.DTO.OrderDTO;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "'ORDER'")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
//    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "total_amount")
    private Double totalAmount;
//    @JdbcTypeCode(SqlTypes.JSON)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order(@NonNull OrderDTO orderDTO, @NonNull Customer customer){
        this.orderId = orderDTO.getOrderId();
        this.customer = customer;
        this.orderDate = orderDTO.getOrderDate();
        this.totalAmount = orderDTO.getTotalAmount();
//        this.orderItems = orderItems;

    }

    public Order(Long orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }
}
