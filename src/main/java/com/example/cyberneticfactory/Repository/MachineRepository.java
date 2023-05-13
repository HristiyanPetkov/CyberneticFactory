package com.example.cyberneticfactory.Repository;

import com.example.cyberneticfactory.Entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
