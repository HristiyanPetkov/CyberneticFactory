package com.example.cyberneticfactory.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class MaterialResource {
    private Long id;
    private String type;
    private List<StorageResource> storages;
    private List<PartResource> part;
}
