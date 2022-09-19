package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autos.concesionaria.entity.Calle;

@Repository
public interface CalleRepository extends JpaRepository<Calle, Long> {
}
