package com.poly.lab6.DAO;

import com.poly.lab6.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
