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
    private String type;

    @ManyToMany
    @JoinTable(
            name = "part_material",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials;

    @OneToMany(mappedBy = "part")
    private List<Machine> machines;
}
