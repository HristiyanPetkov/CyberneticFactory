package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PackageResource {
    private Long id;
    private String name;
    private int price;
    private Date date;
    private List<ProductResource> products;
}
