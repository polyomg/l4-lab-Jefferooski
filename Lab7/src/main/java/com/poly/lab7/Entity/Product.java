package com.poly.lab7.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products") // specify table name
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Integer id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Assuming a many-to-one relationship with Category

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date") // match DB column name
    private Date createDate;


    public Product() {
    }

    public Product(String name, Double price, Date createDate) {
        this.name = name;
        this.price = price;
        this.createDate = createDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                '}';
    }
}
