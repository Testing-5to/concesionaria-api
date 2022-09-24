package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autos.concesionaria.entity.Direccion;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    public List<Direccion> findAllByLocalidad_Nombre(String localidad);
}
