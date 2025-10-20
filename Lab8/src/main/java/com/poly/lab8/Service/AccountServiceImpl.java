package com.poly.lab8.Service;

import com.poly.lab8.Entity.Account;
import com.poly.lab8.Repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account findById(String username) {
        return accountDAO.findById(username).orElse(null);
    }
}
