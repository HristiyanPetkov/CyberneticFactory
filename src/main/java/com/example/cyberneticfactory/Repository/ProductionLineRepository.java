package com.example.cyberneticfactory.Repository;

import com.example.cyberneticfactory.Entity.ProductionLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionLineRepository extends JpaRepository<ProductionLine, Long> {
}
