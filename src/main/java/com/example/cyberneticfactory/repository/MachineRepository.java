package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findAllByPartId(Long id);

    List<Machine> findAllByProductionLineId(Long id);
}
