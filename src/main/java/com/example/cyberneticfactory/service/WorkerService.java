package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.WorkerResource;

import java.util.List;

public interface WorkerService {
    List<WorkerResource> getAll();
    WorkerResource getById(Long id);
    WorkerResource save(WorkerResource worker);
    WorkerResource update(WorkerResource worker, long id);
    void delete(Long id);
}
