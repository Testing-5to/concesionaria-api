package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository <Marca, Long> {

}
