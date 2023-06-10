package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.ProductionLineResource;
import com.example.cyberneticfactory.entity.ProductionLine;
import com.example.cyberneticfactory.repository.MachineRepository;
import com.example.cyberneticfactory.repository.ProductRepository;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.repository.WorkerRepository;
import com.example.cyberneticfactory.service.ProductionLineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.ProductionLineMapper.PRODUCTION_LINE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductionLineServiceImpl implements ProductionLineService {
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
        productionLineToSave.setProduct(null);

        return PRODUCTION_LINE_MAPPER.toProductionLineResource(productionLineRepository.save(productionLineToSave));
    }

    @Override
    public ProductionLineResource update(ProductionLineResource productionLine, long id) {
        ProductionLine productionLineToUpdate = productionLineRepository.getReferenceById(id);
        productionLineToUpdate.setName(productionLine.getName());
        productionLineToUpdate.setProduction_rate(productionLine.getProduction_rate());
        productRepository.getProductByName(productionLine.getProduct())
                .ifPresentOrElse(
                        productionLineToUpdate::setProduct,
                        () -> {
                            throw new EntityNotFoundException("Product not found");
                        }
                );

        return PRODUCTION_LINE_MAPPER.toProductionLineResource(productionLineRepository.save(productionLineToUpdate));
    }

    @Override
    public void delete(Long id) {
        workerRepository.findAllByProductionLineId(id).forEach(worker -> {
            worker.setProductionLine(null);
            workerRepository.save(worker);
        });

        machineRepository.findAllByProductionLineId(id).forEach(machine -> {
            machine.setProductionLine(null);
            machineRepository.save(machine);
        });

        productionLineRepository.deleteById(id);
    }
}
