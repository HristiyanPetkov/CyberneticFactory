package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.ProductionLineResource;
import com.example.cyberneticfactory.entity.Machine;
import com.example.cyberneticfactory.entity.Product;
import com.example.cyberneticfactory.entity.ProductionLine;
import com.example.cyberneticfactory.entity.Worker;
import com.example.cyberneticfactory.repository.MachineRepository;
import com.example.cyberneticfactory.repository.ProductRepository;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.repository.WorkerRepository;
import com.example.cyberneticfactory.service.ProductionLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.ProductionLineMapper.PRODUCTION_LINE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductLineServiceImpl implements ProductionLineService {
    private final ProductionLineRepository productionLineRepository;
    private final WorkerRepository workerRepository;
    private final MachineRepository machineRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductionLineResource> getAll() {
        return PRODUCTION_LINE_MAPPER.toProductionLineResources(productionLineRepository.findAll());
    }

    @Override
    public ProductionLineResource getById(Long id) {
        return PRODUCTION_LINE_MAPPER.toProductionLineResource(productionLineRepository.getReferenceById(id));
    }

    @Override
    public ProductionLineResource save(ProductionLineResource productionLine) {
        ProductionLine productionLineToSave = PRODUCTION_LINE_MAPPER.fromProductionLineResource(productionLine);
        productionLineToSave.setWorkers(null);
        productionLineToSave.setMachines(null);
        productionLineToSave.setProduct(productRepository.getReferenceByName(productionLine.getProduct()));

        return PRODUCTION_LINE_MAPPER.toProductionLineResource(productionLineRepository.save(productionLineToSave));
    }

    @Override
    public ProductionLineResource update(ProductionLineResource productionLine, long id) {
        ProductionLine productionLineToUpdate = productionLineRepository.getReferenceById(id);
        productionLineToUpdate.setName(productionLine.getName());
        productionLineToUpdate.setProduction_rate(productionLine.getProduction_rate());

        return PRODUCTION_LINE_MAPPER.toProductionLineResource(productionLineRepository.save(productionLineToUpdate));
    }

    @Override
    public void delete(Long id) {
        Worker worker = workerRepository.findByProductionLineId(id);
        worker.setProductionLine(null);
        workerRepository.save(worker);

        Machine machine = machineRepository.findByProductionLineId(id);
        machine.setProductionLine(null);
        machineRepository.save(machine);

        Product product = productRepository.findByProductionLineId(id);
        product.setProductionLine(null);
        productRepository.save(product);

        productionLineRepository.deleteById(id);
    }
}