package com.poly.lab6.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
