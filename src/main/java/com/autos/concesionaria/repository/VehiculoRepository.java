package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Vehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    public List<Vehiculo> findAllByModelo_Nombre(String modelo);
}
