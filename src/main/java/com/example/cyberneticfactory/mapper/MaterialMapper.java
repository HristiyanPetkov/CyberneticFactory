package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.MaterialResource;
import com.example.cyberneticfactory.entity.Material;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MaterialMapper {

    MaterialMapper MATERIAL_MAPPER = Mappers.getMapper(MaterialMapper.class);

    Material fromMaterialResource(MaterialResource materialResource);

    MaterialResource toMaterialResource(Material material);

    List<MaterialResource> toMaterialResources(List<Material> materials);
}
