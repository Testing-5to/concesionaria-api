package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autos.concesionaria.entity.TipoVehiculo;

@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {

}
