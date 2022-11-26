package com.autos.concesionaria.repository;

import com.autos.concesionaria.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    public List<Venta> findAllByVehiculo_Id(Long id);

    public int countAllByVendedor_Id(Long id);

    public int countAllByCliente_Id(Long id);

    @Query(
            value = "SELECT " +
                    "SUM((venta.precio_unitario - vehiculo.precio_compra) * venta.cantidad_vehiculos) as \"utilidades\", " +
                    "(SUM((venta.precio_unitario - vehiculo.precio_compra) * venta.cantidad_vehiculos) / SUM(venta.cantidad_vehiculos)) as \"promedio_utilidades\", " +
                    "SUM(venta.cantidad_vehiculos) as \"cantidad_vehiculos\", " +
                    "empleado.nombre as \"vendedor\" " +
                    "FROM venta " +
                    "INNER JOIN empleado ON (venta.empleado_id = empleado.id)" +
                    "INNER JOIN vehiculo ON (venta.vehiculo_id = vehiculo.id)" +
                    "WHERE " +
                    "fecha >= ?1 AND " +
                    "fecha <= ?2 AND " +
                    "empleado_id IN (?3) " +
                    "GROUP BY " +
                    "empleado_id, empleado.nombre",
            nativeQuery = true
    )
    public ArrayList<Object[]> getUtilidades(LocalDate fechaInicio, LocalDate fechaFin, List<Long> empleados);

    @Query(
            value = "SELECT " +
                    "SUM((venta.precio_unitario - vehiculo.precio_compra) * venta.cantidad_vehiculos) as \"utilidades\", " +
                    "(SUM((venta.precio_unitario - vehiculo.precio_compra) * venta.cantidad_vehiculos) / SUM(venta.cantidad_vehiculos)) as \"promedio_utilidades\", " +
                    "SUM(venta.cantidad_vehiculos) as \"cantidad_vehiculos\", " +
                    "empleado.nombre as \"vendedor\" " +
                    "FROM venta " +
                    "INNER JOIN empleado ON (venta.empleado_id = empleado.id)" +
                    "INNER JOIN vehiculo ON (venta.vehiculo_id = vehiculo.id)" +
                    "WHERE " +
                    "fecha >= ?1 AND " +
                    "fecha <= ?2 " +
                    "GROUP BY " +
                    "empleado_id, empleado.nombre",
            nativeQuery = true
    )
    public ArrayList<Object[]> getUtilidades(LocalDate fechaInicio, LocalDate fechaFin);


    @Query(
            value = "SELECT " +
                    "marca.nombre AS \"marca\", " +
                    "empleado.nombre || ' ' || empleado.apellido AS \"vendedor\",\n" +
                    "SUM(venta.cantidad_vehiculos) AS \"cantidad autos vendidos\"\n" +
                    "FROM venta " +
                    "INNER JOIN empleado ON (venta.empleado_id = empleado.id) " +
                    "INNER JOIN vehiculo ON (venta.vehiculo_id = vehiculo.id) " +
                    "INNER JOIN modelo ON (vehiculo.modelo_id = modelo.id) " +
                    "INNER JOIN marca ON (modelo.marca_id = marca.id) " +
                    "WHERE " +
                    "fecha >= ?1 AND " +
                    "fecha <= ?2 AND " +
                    "empleado_id IN (?3) " +
                    "GROUP BY " +
                    "empleado_id, \"vendedor\", marca.nombre",
            nativeQuery = true
    )
    public ArrayList<Object[]> getAutosVendidos(LocalDate fechaInicio, LocalDate fechaFin, List<Long> empleados);

    @Query(
            value = "SELECT " +
                    "marca.nombre AS \"marca\", " +
                    "empleado.nombre || ' ' || empleado.apellido AS \"vendedor\",\n" +
                    "SUM(venta.cantidad_vehiculos) AS \"cantidad autos vendidos\"\n" +
                    "FROM venta " +
                    "INNER JOIN empleado ON (venta.empleado_id = empleado.id) " +
                    "INNER JOIN vehiculo ON (venta.vehiculo_id = vehiculo.id) " +
                    "INNER JOIN modelo ON (vehiculo.modelo_id = modelo.id) " +
                    "INNER JOIN marca ON (modelo.marca_id = marca.id) " +
                    "WHERE " +
                    "fecha >= ?1 AND " +
                    "fecha <= ?2 " +
                    "GROUP BY " +
                    "empleado_id, \"vendedor\", marca.nombre",
            nativeQuery = true
    )
    public ArrayList<Object[]> getAutosVendidos(LocalDate fechaInicio, LocalDate fechaFin);

}
