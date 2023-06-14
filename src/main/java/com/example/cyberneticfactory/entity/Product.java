package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Audited
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private String type;
    private String description;
    private Timestamp validFrom;

    @ManyToMany(mappedBy = "products")
    @NotAudited
    private List<Package> packages;

    @OneToOne()
    @JoinColumn(name = "productionLine_id", referencedColumnName = "id")
    @NotAudited
    private ProductionLine productionLine;
}
