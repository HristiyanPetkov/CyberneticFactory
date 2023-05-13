package com.example.cyberneticfactory.Repository;

import com.example.cyberneticfactory.Entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
