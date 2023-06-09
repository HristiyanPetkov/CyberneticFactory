package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.PartResource;

import java.util.List;

public interface PartService {
    List<PartResource> getAll();
    PartResource getById(Long id);
    PartResource save(PartResource part);
    PartResource update(PartResource part, long id);
    void delete(Long id);
}
