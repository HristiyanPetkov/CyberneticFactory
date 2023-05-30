package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class ProductionLineResource {
    private Long id;
    private String name;
    private int production_rate;
    private List<WorkerResource> workers;
    private List<MachineResource> machines;
    private String product;
}
