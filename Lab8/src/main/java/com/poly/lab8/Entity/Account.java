package com.poly.lab8.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;

    public boolean isAdmin() {
        // Assuming you have a role system or a simple check like below
        return this.username.equals("admin"); // Simplified example
    }

}
