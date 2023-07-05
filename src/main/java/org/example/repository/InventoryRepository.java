package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Transactional
    public List<Inventory> findInventoriesBySize(Double size);

    @Query(value = "SELECT size FROM Inventory INNER JOIN Product on Inventory.product_id = Product.product_id WHERE Inventory.quantity > 0 and Product.brand in :brands", nativeQuery = true)
    List<Double> getSizesByBrands(@Param("brands") Collection<String> brands);

    @Query(value = "SELECT size FROM Inventory WHERE Inventory.quantity > 0", nativeQuery = true)
    List<Double> getAllSizes();

//    @Transactional
//    public List<Long> findProductIdBySize(Integer size);
}
