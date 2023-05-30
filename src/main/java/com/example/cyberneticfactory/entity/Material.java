package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Material {
    @Id
    @GeneratedValue
    private Long id;

    private String type;

    @ManyToMany(mappedBy = "materials")
    private List<Storage> storages;

    @ManyToMany(mappedBy = "materials")
    private List<Part> parts;
}
