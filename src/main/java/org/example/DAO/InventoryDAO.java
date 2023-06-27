package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.example.DTO.InventoryDTO;
import org.example.model.Inventory;
import org.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryDAO {

    private final EntityManager em;

    public List<Long> getProductIdsBySize(List<Integer> sizes) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Inventory> criteriaQuery = criteriaBuilder.createQuery(Inventory.class);

        Root<Inventory> root = criteriaQuery.from(Inventory.class);

        Expression<Integer> exp = root.get("size");
        Predicate predicate = exp.in(sizes);

        criteriaQuery.where(criteriaBuilder.and(Collections.singletonList(predicate).toArray(Predicate[]::new)));

        TypedQuery<Inventory> query = em.createQuery(criteriaQuery);

        return query.getResultStream().map(inventory -> new InventoryDTO(inventory).getProductId()).toList();
    }
}
