package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    public List<Venta> findAllByVehiculo_Id(Long id);

    public int countAllByVendedor_Id(Long id);

    public int countAllByCliente_Id(Long id);
}
