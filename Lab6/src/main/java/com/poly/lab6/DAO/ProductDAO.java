package com.poly.lab6.DAO;

import com.poly.lab6.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Integer> {
}
