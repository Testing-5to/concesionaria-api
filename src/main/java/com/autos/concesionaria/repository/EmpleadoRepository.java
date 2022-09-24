package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    public List<Empleado> findAllByRol_Nombre(String rol);
    public int countByDireccion_Id(Long direccion);
}
