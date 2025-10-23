package com.poly.lab6.DAO;

import com.poly.lab6.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account,Integer> {
}
