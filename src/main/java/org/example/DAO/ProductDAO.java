package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.DTO.ProductDTO;
import org.example.model.Inventory;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAO {

    private final EntityManager em;
    @Autowired
    InventoryDAO inventoryDAO;
    @Autowired
    InventoryRepository inventoryRepository;

    public Page<ProductDTO> getProducts(
            Long productId,
            String productName,
            String description,
            List<String> brands,
            List<Double> sizes,
            String category,
            String sortType,
            String sortOrder,
            int pageNum,
            int pageSize
    ) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (productId != null) {
            Predicate productIdPredicate = criteriaBuilder.equal(root.get("id"), productId);
            predicates.add(productIdPredicate);
        }

        if (productName != null) {
            Predicate productNamePredicate = criteriaBuilder.like(root.get("product_name"), "%" + productName + "%");
            predicates.add(productNamePredicate);
        }

        if (description != null) {
            Predicate descriptionPredicate = criteriaBuilder.like(root.get("description"), "%" + description + "%");
            predicates.add(descriptionPredicate);
        }

        if (brands != null) {
//            Predicate brandPredicate = criteriaBuilder
//                    .like(root.get("brand"), "%" + brand + "%");

            Expression<String> exp = root.get("brand");
            Predicate brandPredicate = exp.in(brands);
            predicates.add(brandPredicate);
        }

        if (category != null) {
            Predicate categoryPredicate = criteriaBuilder.like(root.get("category"), "%" + category + "%");
            predicates.add(categoryPredicate);
        }

        if (sizes != null) {
            Expression<Long> expSize = root.get("id");
//            Predicate sizePredicate = expSize.in(inventoryDAO.getProductIdsBySize(sizes));
            Predicate sizePredicate = expSize.in(sizes.stream().map(inventoryRepository::findInventoriesBySize).map(inventories -> inventories.stream().map(inventory -> inventory.getProduct().getProductId()).toList()).toList());
//            Predicate sizePredicate = expSize.in(sizes.stream().map(inventoryRepository::findProductIdBySize).toList());
            predicates.add(sizePredicate);
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new)));

        if (sortType != null && sortOrder != null) {
            if (sortOrder.equals("desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortType)));
            } else if (sortOrder.equals("asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortType)));
            }
        }
        TypedQuery<Product> query = em.createQuery(criteriaQuery);

        int allProdSize = query.getResultList().size();
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        query.setFirstResult((pageNum) * pageSize);
        query.setMaxResults(pageSize);
        List<Product> products = query.getResultList();

//        return products.stream().map(ProductDTO::new).toList();
        List<ProductDTO> productDTOList = products.stream().map(ProductDTO::new).toList();
        return new PageImpl<>(productDTOList, pageable, allProdSize);
    }



    public HashSet<Double> getSizes(List<String> brands) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if(brands != null) {
            Expression<String> exp = root.get("brand");
            Predicate brandPredicate = exp.in(brands);
            predicates.add(brandPredicate);
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new)));
        TypedQuery<Product> query = em.createQuery(criteriaQuery);
        List<Long> productIds = query.getResultList().stream().map(Product::getProductId).toList();




        CriteriaBuilder criteriaBuilder2 = em.getCriteriaBuilder();
        CriteriaQuery<Inventory> criteriaQuery2 = criteriaBuilder2.createQuery(Inventory.class);
        Root<Inventory> root2 = criteriaQuery2.from(Inventory.class);
        List<Predicate> predicates2 = new ArrayList<>();

        Expression<Long> exp = root2.get("product_id");
        Predicate sizePredicate = exp.in(productIds);
        predicates2.add(sizePredicate);

        criteriaQuery2.where(criteriaBuilder2.and(predicates2.toArray(Predicate[]::new)));
        TypedQuery<Inventory> query2 = em.createQuery(criteriaQuery2);
        List<Double> sizes = query2.getResultList().stream().map(Inventory::getSize).toList();
        return new HashSet<>(sizes);
    }
}
