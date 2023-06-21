package org.example.repository;

import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT brand FROM Product INNER JOIN Inventory on Product.product_id = Inventory.product_id WHERE Inventory.quantity > 0", nativeQuery = true)
    List<String> getAvailableBrands();
}
