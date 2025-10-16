package com.poly.lab6.DAO;

import com.poly.lab6.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetail,Integer> {
}
