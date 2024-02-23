package com.test.restfulservice.entity;

import jakarta.persistence.*;

@Entity // specifies that this class is an entity and should be mapped to a database table
@Table(name = "product") // specifies the name of the database table
public class Product {
    @Id // specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that key should be automatically generated
    private long key;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    public long getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
