package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class ProductResource {
    private Long id;
    private String name;
    private int price;
    private String type;
    private String description;
    private List<PackageResource> packages;
    private String productionLine;
}
