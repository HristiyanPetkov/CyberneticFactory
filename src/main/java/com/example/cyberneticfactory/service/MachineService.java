package com.example.cyberneticfactory.service;

import com.example.cyberneticfactory.controller.resources.MachineResource;

import java.util.List;

public interface MachineService {
    List<MachineResource> getAll();
    MachineResource getById(Long id);
    MachineResource save(MachineResource machine);
    MachineResource update(MachineResource machine, long id);
    void delete(Long id);
}
