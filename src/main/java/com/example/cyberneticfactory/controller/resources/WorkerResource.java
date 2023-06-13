package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class WorkerResource {
    private Long id;
    private String name;
    private int salary;
    private int hours;
    private int days;
    private Date startDate;
    private Date endDate;
    private String productionLine;
    private Timestamp createdDate;
}
