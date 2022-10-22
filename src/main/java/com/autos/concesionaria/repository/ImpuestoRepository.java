package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {

}
