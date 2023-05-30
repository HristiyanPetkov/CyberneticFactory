package com.example.cyberneticfactory.mapper;

import com.example.cyberneticfactory.controller.resources.WorkerResource;
import com.example.cyberneticfactory.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkerMapper {

    WorkerMapper WORKER_MAPPER = Mappers.getMapper(WorkerMapper.class);

    @Mapping(target = "productionLine.name", source = "workerResource.productionLine")
    Worker fromWorkerResource(WorkerResource workerResource);

    @Mapping(target = "productionLine", source = "worker.productionLine.name")
    WorkerResource toWorkerResource(Worker worker);

    List<WorkerResource> toWorkerResources(List<Worker> workers);
}
