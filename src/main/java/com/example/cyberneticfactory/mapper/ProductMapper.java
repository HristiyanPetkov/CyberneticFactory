package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.ProductResource;
import com.example.cyberneticfactory.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PackageMapper.class})
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productionLine.name", source = "productResource.productionLine")
    Product fromProductResource(ProductResource productResource);

    @Mapping(target = "productionLine", source = "product.productionLine.name")
    ProductResource toProductResource(Product product);

    List<ProductResource> toProductResources(List<Product> products);
}
