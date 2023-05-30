package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.PartResource;
import com.example.cyberneticfactory.entity.Machine;
import com.example.cyberneticfactory.entity.Material;
import com.example.cyberneticfactory.entity.Part;
import com.example.cyberneticfactory.repository.MachineRepository;
import com.example.cyberneticfactory.repository.MaterialRepository;
import com.example.cyberneticfactory.repository.PartRepository;
import com.example.cyberneticfactory.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.PartMapper.PART_MAPPER;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    PartRepository partRepository;
    MaterialRepository materialRepository;
    MachineRepository machineRepository;

    @Override
    public List<PartResource> getAll() {
        return PART_MAPPER.toPartResources(partRepository.findAll());
    }

    @Override
    public PartResource getById(Long id) {
        return PART_MAPPER.toPartResource(partRepository.getReferenceById(id));
    }

    @Override
    public PartResource save(PartResource part) {
        Part partToSave = PART_MAPPER.fromPartResource(part);
        partToSave.setName(part.getName());
        partToSave.setType(part.getType());

        return PART_MAPPER.toPartResource(partRepository.save(partToSave));
    }

    @Override
    public PartResource update(PartResource part, long id) {
        Part partToUpdate = partRepository.getReferenceById(id);
        partToUpdate.setName(part.getName());
        partToUpdate.setType(part.getType());

        return PART_MAPPER.toPartResource(partRepository.save(partToUpdate));
    }

    @Override
    public void delete(Long id) {
        machineRepository.findAllByPartId(id).forEach(this::removePartFromMachine);
        materialRepository.findAllByParts_id(id).forEach(material -> removePartFromMaterial(material, id));
        partRepository.deleteById(id);
    }

    private void removePartFromMaterial(Material material, long id) {
        material.getParts().removeIf(part -> part.getId().equals(id));
        materialRepository.save(material);
    }

    private void removePartFromMachine(Machine machine) {
        machine.setPart(null);
        machineRepository.save(machine);
    }
}
