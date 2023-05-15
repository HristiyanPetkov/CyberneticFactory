package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Package, Long> {
}
