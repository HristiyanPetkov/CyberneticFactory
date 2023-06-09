package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.PackageResource;

import java.util.List;

public interface PackageService {
    List<PackageResource> getAll();
    PackageResource getById(Long id);
    PackageResource save(PackageResource aPackage);
    PackageResource update(PackageResource aPackage, long id);
    void delete(Long id);
}
