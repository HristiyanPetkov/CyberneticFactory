package com.example.cyberneticfactory.service.impl;

import com.example.cyberneticfactory.controller.resources.WorkerResource;
import com.example.cyberneticfactory.entity.Worker;
import com.example.cyberneticfactory.repository.ProductionLineRepository;
import com.example.cyberneticfactory.repository.WorkerRepository;
import com.example.cyberneticfactory.service.WorkerService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.example.cyberneticfactory.mapper.WorkerMapper.WORKER_MAPPER;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ProductionLineRepository productionLineRepository;
    private final EntityManagerFactory entityManagerFactory;

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

    @Override
    public Object getAudits() {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        return auditReader.createQuery()
                .forRevisionsOfEntity(Worker.class, true, true)
                .getResultList();
    }

    @Override
    public Object getAuditsById(long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        return auditReader.createQuery()
                .forRevisionsOfEntity(Worker.class, true, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();
    }

    @Override
    public Object getAuditsByDate(String date) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<?> revisions = auditReader.createQuery()
                .forRevisionsOfEntity(Worker.class, true, true)
                .getResultList();

        Object ret_val = null;

        for (Object revision : revisions) {

            if (((Worker) revision).getCreatedDate() == null) {
                break;
            }

            if (((Worker) revision).getCreatedDate().before(Timestamp.valueOf(date))
                    || ((Worker) revision).getCreatedDate().equals(Timestamp.valueOf(date))) {
                ret_val = revision;
            }
        }

        return ret_val;
    }
}
