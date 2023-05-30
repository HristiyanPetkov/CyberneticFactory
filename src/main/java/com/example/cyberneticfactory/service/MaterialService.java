package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.MaterialResource;

import java.util.List;

public interface MaterialService {
    List<MaterialResource> getAll();
    MaterialResource getById(Long id);
    MaterialResource save(MaterialResource material);
    MaterialResource update(MaterialResource material, long id);
    void delete(Long id);
}
