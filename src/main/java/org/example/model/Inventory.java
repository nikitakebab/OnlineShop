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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "size")
    private Double size;

    public Inventory(@NonNull InventoryDTO inventoryDTO, @NonNull Product product){
        this.inventoryId = inventoryDTO.getInventoryId();
        this.product = product;
        this.quantity = inventoryDTO.getQuantity();
        this.size = inventoryDTO.getSize();
    }

    public Inventory(Long inventoryId, Product product) {
        this.inventoryId = inventoryId;
        this.product = product;
    }
}
