package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.ProductResource;

import java.util.List;

public interface ProductService {
    List<ProductResource> getAll();
    ProductResource getById(Long id);
    ProductResource save(ProductResource product);
    ProductResource update(ProductResource product, long id);
    void delete(Long id);
}
