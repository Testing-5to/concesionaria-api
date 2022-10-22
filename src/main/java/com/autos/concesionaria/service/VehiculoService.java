package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Vehiculo;
import com.autos.concesionaria.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    @Autowired
    // Inyectamos el repositorio
    private VehiculoRepository vehiculoRepository;


    /**
     * Crear un vehiculo
     *
     * @param vehiculo
     * @return vehiculo created
     */
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoRepository.save(vehiculo);
        return nuevoVehiculo;
    }

    /**
     * Obtener todos los vehiculos
     *
     * @return List<vehiculo> Lista de todos los vehiculos
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Obtener una lista de vehiculos por modelo
     *
     * @param modelo
     * @return List<vehiculo> List of vehiculos
     */
    public List<Vehiculo> getVehiculosByModelo(String modelo) {
        return vehiculoRepository.findAllByModelo_Nombre(modelo);
    }

    /**
     * Obtener un vehiculo por id
     *
     * @param id
     * @return vehiculo found or null
     */
    public Vehiculo getVehiculo(Long id) {
        return vehiculoRepository.findById(id).get();
    }

    /**
     * Actualizar un vehiculo
     *
     * @param id    vehiculo id
     * @param vehiculo vehiculo data to update
     * @return vehiculo updated
     */
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculo) {
        Vehiculo vehiculoActual = vehiculoRepository.findById(id).get();
        vehiculoActual.setAnio(vehiculo.getAnio());
        vehiculoActual.setCantidad(vehiculo.getCantidad());
        vehiculoActual.setImportado(vehiculo.getImportado());
        vehiculoActual.setImagen(vehiculo.getImagen());
        vehiculoActual.setPrecioCompra(vehiculo.getPrecioCompra());
        vehiculoActual.setPrecioVenta(vehiculo.getPrecioVenta());
        vehiculoActual.setModelo(vehiculo.getModelo());
        return vehiculoRepository.save(vehiculoActual);
    }

    /**
     * Eliminar un vehiculo
     *
     * @param id vehiculo id to delete
     */
    public void borrarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

}
