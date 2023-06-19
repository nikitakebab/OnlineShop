package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.DTO.InventoryDTO;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;
//    @JdbcTypeCode(SqlTypes.JSON)
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private int quantity;

    public Inventory(@NonNull InventoryDTO inventoryDTO, @NonNull Product product){
        this.inventoryId = inventoryDTO.getInventoryId();
        this.product = product;
        this.quantity = inventoryDTO.getQuantity();
    }
}
