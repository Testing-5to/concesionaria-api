package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {
    public Impuesto findByRegion(String region);
    public List<Impuesto> findAllByRegion(String region);
}
