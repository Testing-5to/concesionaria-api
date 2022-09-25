package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Vehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
