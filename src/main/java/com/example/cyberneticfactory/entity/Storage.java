package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Storage {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int capacity;
    private int used;

    @ManyToMany
    @JoinTable(
            name = "storage_material",
            joinColumns = @JoinColumn(name = "storage_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;
}
