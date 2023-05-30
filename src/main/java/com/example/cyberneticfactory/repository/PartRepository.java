package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Part getReferenceByName(String part);

    List<Part> findAllByMaterials_Id(Long id);

    Part findByMachines_Id(Long id);

    Optional<Part> getPartsByName(String name);
}
