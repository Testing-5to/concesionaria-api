package com.autos.concesionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autos.concesionaria.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
