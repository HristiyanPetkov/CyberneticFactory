package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Data
@Audited
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
    @NotAudited
    private List<Material> materials;
}
