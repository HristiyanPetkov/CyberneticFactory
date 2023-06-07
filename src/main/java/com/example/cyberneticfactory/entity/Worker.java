package com.example.cyberneticfactory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

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
    private Date StartDate;
    private Date EndDate;
    
    @ManyToOne
    private ProductionLine productionLine;
}
