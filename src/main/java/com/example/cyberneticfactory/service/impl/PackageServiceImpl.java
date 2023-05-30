package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.PackageResource;
import com.example.cyberneticfactory.entity.Package;
import com.example.cyberneticfactory.entity.Product;
import com.example.cyberneticfactory.repository.PackageRepository;
import com.example.cyberneticfactory.repository.ProductRepository;
import com.example.cyberneticfactory.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.PackageMapper.PACKAGE_MAPPER;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {
    PackageRepository packageRepository;
    ProductRepository productRepository;

    @Override
    public List<PackageResource> getAll() {
        return PACKAGE_MAPPER.toPackageResources(packageRepository.findAll());
    }

    @Override
    public PackageResource getById(Long id) {
        return PACKAGE_MAPPER.toPackageResource(packageRepository.getReferenceById(id));
    }

    @Override
    public PackageResource save(PackageResource aPackage) {
        Package aPackageToSave = PACKAGE_MAPPER.fromPackageResource(aPackage);
        aPackageToSave.setProducts(null);

        return PACKAGE_MAPPER.toPackageResource(packageRepository.save(aPackageToSave));
    }

    @Override
    public PackageResource update(PackageResource aPackage, long id) {
        Package aPackageToUpdate = packageRepository.getReferenceById(id);
        aPackageToUpdate.setName(aPackage.getName());
        aPackageToUpdate.setPrice(aPackage.getPrice());
        aPackageToUpdate.setDate(aPackage.getDate());

        return PACKAGE_MAPPER.toPackageResource(packageRepository.save(aPackageToUpdate));
    }

    @Override
    public void delete(Long id) {
        productRepository.findAllByPackages_Id(id).forEach(product -> removePackageFromProduct(product, id));
        packageRepository.deleteById(id);
    }

    private void removePackageFromProduct(Product product, long id) {
        product.getPackages().removeIf(aPackage -> aPackage.getId().equals(id));
        productRepository.save(product);
    }
}
