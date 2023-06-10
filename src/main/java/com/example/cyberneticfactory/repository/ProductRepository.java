package com.example.cyberneticfactory.repository;

import com.example.cyberneticfactory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPackages_Id(Long id);

    Optional<Product> getProductByName(String product);
}
