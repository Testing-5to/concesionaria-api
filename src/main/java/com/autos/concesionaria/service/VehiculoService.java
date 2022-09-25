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
    // Repository injected by constructor
    private VehiculoRepository vehiculoRepository;


    /**
     * Create a vehiculo
     *
     * @param vehiculo
     * @return vehiculo created
     */
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoRepository.save(vehiculo);
        return nuevoVehiculo;
    }

    /**
     * Get all the vehiculos
     *
     * @return List<vehiculo> List of vehiculos
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Get all the vehiculos by modelo
     *
     * @param modelo
     * @return List<vehiculo> List of vehiculos
     */
    public List<Vehiculo> getVehiculosByModelo(String modelo) {
        return vehiculoRepository.findAllByModelo_Nombre(modelo);
    }

    /**
     * Get a vehiculo by id
     *
     * @param id
     * @return vehiculo found or null
     */
    public Vehiculo getVehiculo(Long id) {
        return vehiculoRepository.findById(id).get();
    }

    /**
     * Update a vehiculo
     *
     * @param id    vehiculo id
     * @param vehiculo vehiculo data to update
     * @return vehiculo updated
     */
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculo) {
        Vehiculo vehiculoActual = vehiculoRepository.findById(id).get();
        vehiculoActual.setNombre(vehiculo.getNombre());
        vehiculoActual.setAnio(vehiculo.getAnio());
        vehiculoActual.setKilometros(vehiculo.getKilometros());
        vehiculoActual.setImportado(vehiculo.getImportado());
        vehiculoActual.setImagen(vehiculo.getImagen());
        vehiculoActual.setPrecioCompra(vehiculo.getPrecioCompra());
        vehiculoActual.setPrecioVenta(vehiculo.getPrecioVenta());
        vehiculoActual.setModelo(vehiculo.getModelo());
        return vehiculoRepository.save(vehiculoActual);
    }

    /**
     * Delete a vehiculo
     *
     * @param id vehiculo id to delete
     */
    public void borrarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

}
