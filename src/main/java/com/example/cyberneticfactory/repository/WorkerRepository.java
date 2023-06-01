package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findAllByProductionLineId(Long id);
}
