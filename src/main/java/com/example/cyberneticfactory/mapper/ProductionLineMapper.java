package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.ProductionLineResource;
import com.example.cyberneticfactory.entity.ProductionLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MachineMapper.class, WorkerMapper.class})
public interface ProductionLineMapper {

    ProductionLineMapper MAPPER = Mappers.getMapper(ProductionLineMapper.class);

    @Mapping(target = "product.name", source = "product")
    ProductionLine fromProductionLineResource(ProductionLineResource productionLineResource);

    @Mapping(target = "product", source = "productionLine.product.name")
    ProductionLineResource toProductionLineResource(ProductionLine productionLine);

    List<ProductionLineResource> toProductionLineResources(List<ProductionLine> productionLines);
}
