package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Part {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private String type;
    private String description;

    @OneToMany(mappedBy = "part")
    private List<Machine> machines;

    @ManyToOne
    private Order order;
}
