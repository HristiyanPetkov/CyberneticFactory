package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.StorageResource;

import java.util.List;

public interface StorageService {
    List<StorageResource> getAll();
    StorageResource getById(Long id);
    StorageResource save(StorageResource storage);
    StorageResource update(StorageResource storage, long id);
    void delete(Long id);
    StorageResource addMaterial(long id, long materialId);
}
