package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autos.concesionaria.entity.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

}
