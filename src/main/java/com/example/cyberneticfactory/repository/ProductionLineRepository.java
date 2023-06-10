package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.ProductionLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionLineRepository extends JpaRepository<ProductionLine, Long> {
    Optional<ProductionLine> getProductionLineByName(String productionLine);
}
