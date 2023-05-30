package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findAllByParts_id(long id);

    List<Material> findAllByStorages_Id(Long id);
}
