package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Package {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "package_product",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
