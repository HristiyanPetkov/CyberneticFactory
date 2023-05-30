package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.MachineResource;
import com.example.cyberneticfactory.entity.Machine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MachineMapper {
    MachineMapper MACHINE_MAPPER = Mappers.getMapper(MachineMapper.class);

    @Mapping(target = "productionLine.name", source = "machineResource.productionLine")
    @Mapping(target = "part.name", source = "machineResource.part")
    Machine fromMachineResource(MachineResource machineResource);

    @Mapping(target = "productionLine", source = "machine.productionLine.name")
    @Mapping(target = "part", source = "machine.part.name")
    MachineResource toMachineResource(Machine machine);

    List<MachineResource> toMachineResources(List<Machine> machines);
}
