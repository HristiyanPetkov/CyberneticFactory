package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

@Data
public class WorkerResource {
    private Long id;
    private String name;
    private int salary;
    private int hours;
    private int days;
    private int StartDate;
    private int EndDate;
    private String productionLine;
}