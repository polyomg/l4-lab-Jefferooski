package com.poly.lab8.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity(name = "accounts")
@Data
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    @Column(name = "is_admin")
    private Boolean adminFlag; // Add this field to match database

    public boolean isAdmin() {
        // Use the database column instead of hardcoded check
        return this.adminFlag != null && this.adminFlag;
    }

}
