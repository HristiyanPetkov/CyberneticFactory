package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductResource {
    private Long id;
    private String name;
    private int price;
    private String type;
    private String description;
    private String productionLine;
    private Timestamp validFrom;
}
