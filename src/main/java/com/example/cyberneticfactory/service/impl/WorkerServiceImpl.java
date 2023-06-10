package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.WorkerResource;
import com.example.cyberneticfactory.entity.Worker;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.repository.WorkerRepository;
import com.example.cyberneticfactory.service.WorkerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cyberneticfactory.mapper.WorkerMapper.WORKER_MAPPER;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ProductionLineRepository productionLineRepository;

    @Override
    public List<WorkerResource> getAll() {
        return WORKER_MAPPER.toWorkerResources(workerRepository.findAll());
    }

    @Override
    public WorkerResource getById(Long id) {
        return WORKER_MAPPER.toWorkerResource(workerRepository.getReferenceById(id));
    }

    @Override
    public WorkerResource save(WorkerResource worker) {
        Worker workerToSave = WORKER_MAPPER.fromWorkerResource(worker);

        productionLineRepository.getProductionLineByName(workerToSave.getProductionLine().getName())
                .ifPresentOrElse(
                        workerToSave::setProductionLine,
                        () -> {
                            throw new EntityNotFoundException("Production line not found");
                        }
                );

        return WORKER_MAPPER.toWorkerResource(workerRepository.save(workerToSave));
    }

    @Override
    public WorkerResource update(WorkerResource worker, long id) {
        Worker workerToUpdate = workerRepository.getReferenceById(id);
        workerToUpdate.setName(worker.getName());
        workerToUpdate.setSalary(worker.getSalary());
        workerToUpdate.setHours(worker.getHours());
        workerToUpdate.setDays(worker.getDays());
        workerToUpdate.setStartDate(worker.getStartDate());
        workerToUpdate.setEndDate(worker.getEndDate());
        productionLineRepository.getProductionLineByName(worker.getProductionLine())
                .ifPresentOrElse(
                        workerToUpdate::setProductionLine,
                        () -> {
                            throw new EntityNotFoundException("Production line not found");
                        }
                );

        return WORKER_MAPPER.toWorkerResource(workerRepository.save(workerToUpdate));
    }

    @Override
    public void delete(Long id) {
        workerRepository.deleteById(id);
    }
}
