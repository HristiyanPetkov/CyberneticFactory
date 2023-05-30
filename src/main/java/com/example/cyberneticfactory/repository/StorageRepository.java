package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findAllByMaterials_Id(Long id);
}
