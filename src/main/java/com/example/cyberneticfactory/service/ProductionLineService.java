package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.ProductionLineResource;

import java.util.List;

public interface ProductionLineService {
    List<ProductionLineResource> getAll();
    ProductionLineResource getById(Long id);
    ProductionLineResource save(ProductionLineResource productionLine);
    ProductionLineResource update(ProductionLineResource productionLine, long id);
    void delete(Long id);
}
