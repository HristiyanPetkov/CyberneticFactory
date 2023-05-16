package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProductionLine {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int production_rate;

    @OneToMany(mappedBy = "productionLine")
    private List<Worker> workers;

    @OneToMany(mappedBy = "productionLine")
    private List<Machine> machines;

    @OneToOne(mappedBy = "productionLine")
    private Product product;
}
