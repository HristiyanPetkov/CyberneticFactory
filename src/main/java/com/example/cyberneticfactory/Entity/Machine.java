package com.example.cyberneticfactory.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Machine {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int production_rate;

    @ManyToOne
    private Part part;
}
