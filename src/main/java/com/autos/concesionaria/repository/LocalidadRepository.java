package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autos.concesionaria.entity.Localidad;

import java.util.List;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

    public List<Localidad> findAllByProvincia_Nombre(String provincia);

}
