package com.poly.lab7.Repository;

import com.poly.lab7.Entity.Product;
import com.poly.lab7.Model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    // JPQL query to find products within the price range (task 1)
//    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//    Page<Product> findByPrice(double minPrice, double maxPrice, Pageable pageable);

    // task 4
    Page<Product> findByPriceBetween(double minPrice, double maxPrice);

    // task 2
//    @Query("FROM Product o WHERE o.name LIKE ?1")
//    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // task 5
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);

    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count " +
            "FROM Product o " +
            "GROUP BY o.category " +
            "ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();
}

