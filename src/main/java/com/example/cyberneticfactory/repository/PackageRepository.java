package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findAllByProducts_Id(Long id);
}
