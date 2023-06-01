package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.ProductResource;
import com.example.cyberneticfactory.entity.Package;
import com.example.cyberneticfactory.entity.Product;
import com.example.cyberneticfactory.entity.ProductionLine;
import com.example.cyberneticfactory.repository.PackageRepository;
import com.example.cyberneticfactory.repository.ProductRepository;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PackageRepository packageRepository;
    private final ProductionLineRepository productionLineRepository;

    @Override
    public List<ProductResource> getAll() {
        return PRODUCT_MAPPER.toProductResources(productRepository.findAll());
    }

    @Override
    public ProductResource getById(Long id) {
        return PRODUCT_MAPPER.toProductResource(productRepository.getReferenceById(id));
    }

    @Override
    public ProductResource save(ProductResource product) {
        Product productToSave = PRODUCT_MAPPER.fromProductResource(product);
        productToSave.setPackages(null);

        productionLineRepository.getProductionLineByName(productToSave.getProductionLine().getName())
                .ifPresentOrElse(
                        productToSave::setProductionLine,
                        () -> {
                            throw new EntityNotFoundException("Production line not found");
                        }
                );

        return PRODUCT_MAPPER.toProductResource(productRepository.save(productToSave));
    }

    @Override
    public ProductResource update(ProductResource product, long id) {
        Product productToUpdate = productRepository.getReferenceById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setType(product.getType());
        productToUpdate.setDescription(product.getDescription());

        return PRODUCT_MAPPER.toProductResource(productRepository.save(productToUpdate));
    }

    @Override
    public void delete(Long id) {
        ProductionLine productionLine =  productionLineRepository.findByProduct_Id(id);
        productionLine.setProduct(null);
        productionLineRepository.save(productionLine);

        packageRepository.findAllByProducts_Id(id).forEach(aPackage -> removeProductFromPackage(aPackage, id));
        productRepository.deleteById(id);
    }

    private void removeProductFromPackage(Package aPackage, Long id) {
        aPackage.getProducts().removeIf(product -> product.getId().equals(id));
        packageRepository.save(aPackage);
    }
}
