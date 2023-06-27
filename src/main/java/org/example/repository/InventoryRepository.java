package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Transactional
    public List<Inventory> findInventoriesBySize(Integer size);

//    @Transactional
//    public List<Long> findProductIdBySize(Integer size);
}
