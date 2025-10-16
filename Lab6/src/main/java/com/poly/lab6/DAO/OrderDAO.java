package com.poly.lab6.DAO;

import com.poly.lab6.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer> {
}
