package com.example.cyberneticfactory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Audited
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

    @CreatedDate
    private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @NotAudited
    private ProductionLine productionLine;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void preAction() {
        createdDate = new Timestamp(System.currentTimeMillis());
    }
}
