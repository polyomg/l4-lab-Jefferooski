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
        System.out.println("AccountServiceImpl: Looking for username: " + username);
        try {
            Account result = accountDAO.findById(username).orElse(null);
            System.out.println("AccountServiceImpl: Found user: " + (result != null ? result.getUsername() : "null"));
            return result;
        } catch (Exception e) {
            System.out.println("AccountServiceImpl: Error finding user: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
