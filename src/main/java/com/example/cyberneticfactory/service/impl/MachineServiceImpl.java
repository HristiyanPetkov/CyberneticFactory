package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.MachineResource;
import com.example.cyberneticfactory.entity.Machine;
import com.example.cyberneticfactory.repository.MachineRepository;
import com.example.cyberneticfactory.repository.PartRepository;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.service.MachineService;
import jakarta.persistence.EntityNotFoundException;
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
        partRepository.getPartsByName(machineToSave.getPart().getName())
                .ifPresentOrElse(
                    machineToSave::setPart,
                    () -> {
                        throw new EntityNotFoundException("Part not found");
                    }
                );

        productionLineRepository.getProductionLineByName(machineResource.getProductionLine())
                .ifPresentOrElse(
                    machineToSave::setProductionLine,
                    () -> {
                        throw new EntityNotFoundException("Production line not found");
                    }
                );

        return MACHINE_MAPPER.toMachineResource(machineRepository.save(machineToSave));
    }

    @Override
    public MachineResource update(MachineResource machine, long id) {
        Machine machineToUpdate = machineRepository.getReferenceById(id);
        machineToUpdate.setName(machine.getName());
        machineToUpdate.setProduction_rate(machine.getProduction_rate());
        partRepository.getPartsByName(machine.getPart())
                .ifPresentOrElse(
                        machineToUpdate::setPart,
                        () -> {
                            throw new EntityNotFoundException("Part not found");
                        }
                );

        productionLineRepository.getProductionLineByName(machine.getProductionLine())
                .ifPresentOrElse(
                        machineToUpdate::setProductionLine,
                        () -> {
                            throw new EntityNotFoundException("Production line not found");
                        }
                );

        return MACHINE_MAPPER.toMachineResource(machineRepository.save(machineToUpdate));
    }

    @Override
    public void delete(Long id) {
        machineRepository.deleteById(id);
    }
}
