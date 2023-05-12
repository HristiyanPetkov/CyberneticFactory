package com.example.cyberneticfactory.Entity;

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

    @ManyToOne
    private Part part;

    @OneToMany(mappedBy = "productionLine")
    private List<Worker> workers;
}
