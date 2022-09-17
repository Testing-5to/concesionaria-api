package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autos.concesionaria.entity.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

}
