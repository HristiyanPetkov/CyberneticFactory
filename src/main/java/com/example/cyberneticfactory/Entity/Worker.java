package com.example.cyberneticfactory.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Worker {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int salary;
    private int hours;
    private int days;
    private int StartDate;
    private int EndDate;
    
    @ManyToOne
    private ProductionLine productionLine;
}
