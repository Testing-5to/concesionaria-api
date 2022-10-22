package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    public boolean existsByNombre(String nombre);
}
