package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

@Data
public class MachineResource {
    private Long id;
    private String name;
    private int production_rate;
    private String part;
    private String productionLine;
}
