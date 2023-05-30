package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class PartResource {
    private Long id;
    private String name;
    private String type;
    private List<MaterialResource> materials;
    private List<MachineResource> machines;
}
