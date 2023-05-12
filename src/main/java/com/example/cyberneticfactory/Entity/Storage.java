package com.example.cyberneticfactory.Entity;

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

    @ManyToMany(mappedBy = "storage")
    private List<Material> materials;
}
