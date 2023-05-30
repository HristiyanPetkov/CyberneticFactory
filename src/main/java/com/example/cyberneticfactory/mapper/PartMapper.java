package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.PartResource;
import com.example.cyberneticfactory.entity.Part;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MaterialMapper.class, MachineMapper.class})
public interface PartMapper {

    PartMapper MAPPER = Mappers.getMapper(PartMapper.class);

    Part fromPartResource(PartResource partResource);

    PartResource toPartResource(Part part);

    List<PartResource> toPartResources(List<Part> parts);
}
