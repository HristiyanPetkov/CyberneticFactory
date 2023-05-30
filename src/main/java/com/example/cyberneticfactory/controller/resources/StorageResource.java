package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class StorageResource {
    private Long id;
    private String name;
    private int capacity;
    private int used;
    private List<MaterialResource> materials;
}
