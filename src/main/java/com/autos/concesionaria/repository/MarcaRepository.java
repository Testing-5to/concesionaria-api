package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository <Marca, Long> {

}
