package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    public List<Modelo> findAllByMarca_Nombre(String marca);
}
