package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private String type;
    private String description;

    @ManyToMany(mappedBy = "products")
    private List<Package> packages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productionLine_id", referencedColumnName = "id")
    private ProductionLine productionLine;
}
