package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.MachineResource;
import com.example.cyberneticfactory.entity.Machine;
import com.example.cyberneticfactory.entity.Part;
import com.example.cyberneticfactory.entity.ProductionLine;
import com.example.cyberneticfactory.repository.MachineRepository;
import com.example.cyberneticfactory.repository.PartRepository;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.MachineMapper.MACHINE_MAPPER;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;
    private final PartRepository partRepository;
    private final ProductionLineRepository productionLineRepository;

    @Override
    public List<MachineResource> getAll() {
        return MACHINE_MAPPER.toMachineResources(machineRepository.findAll());
    }

    @Override
    public MachineResource getById(Long id) {
        return MACHINE_MAPPER.toMachineResource(machineRepository.getReferenceById(id));
    }

    @Override
    public MachineResource save(MachineResource machineResource) {
        Machine machineToSave = MACHINE_MAPPER.fromMachineResource(machineResource);
        machineToSave.setPart(partRepository.getReferenceByName(machineResource.getPart()));
        machineToSave.setProductionLine(productionLineRepository.getReferenceByName(machineResource.getProductionLine()));

        return MACHINE_MAPPER.toMachineResource(machineRepository.save(machineToSave));
    }

    @Override
    public MachineResource update(MachineResource machine, long id) {
        Machine machineToUpdate = machineRepository.getReferenceById(id);
        machineToUpdate.setName(machine.getName());
        machineToUpdate.setProduction_rate(machine.getProduction_rate());

        return MACHINE_MAPPER.toMachineResource(machineRepository.save(machineToUpdate));
    }

    @Override
    public void delete(Long id) {
        ProductionLine productionLine = productionLineRepository.findByMachines_Id(id);
        productionLine.getMachines().removeIf(machine -> machine.getId().equals(id));
        productionLineRepository.save(productionLine);

        Part part = partRepository.findByMachines_Id(id);
        part.getMachines().removeIf(machine -> machine.getId().equals(id));
        partRepository.save(part);

        machineRepository.deleteById(id);
    }
}
