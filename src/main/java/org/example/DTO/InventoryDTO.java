package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.Inventory;

@Data
@NoArgsConstructor
public class InventoryDTO {
    private Long inventoryId;
    private Long productId;
    private int quantity;

    public InventoryDTO(@NonNull Inventory inventory) {
        this.inventoryId = inventory.getInventoryId();
        this.productId = inventory.getProduct().getProductId();
        this.quantity = inventory.getQuantity();
    }
}

