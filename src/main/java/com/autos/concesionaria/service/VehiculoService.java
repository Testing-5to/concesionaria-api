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

    // Inyectamos el repositorio
    @Autowired
    private VehiculoRepository vehiculoRepository;

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
     * @param String modelo Modelo del vehiculo
     * @return List<vehiculo> Lista de vehiculos por modelo
     */
    public List<Vehiculo> getVehiculosByModelo(String modelo) {
        return vehiculoRepository.findAllByModelo_Nombre(modelo);
    }

    /**
     * Obtener un vehiculo por id
     *
     * @param Long id Id del vehiculo
     * @return Vehiculo encontrado
     */
    public Vehiculo getVehiculo(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    /**
     * Crear un vehiculo
     *
     * @param Vehiculo vehiculo a crear
     * @return Vehiculo creado
     */
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoRepository.save(vehiculo);
        return nuevoVehiculo;
    }

    /**
     * Actualizar un vehiculo
     *
     * @param Long     id del vehiculo
     * @param Vehiculo vehiculo a actualizar
     * @return Vehiculo actualizado
     */
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculo) {
        Vehiculo vehiculoActual = vehiculoRepository.findById(id).orElse(null);
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
     * @param Long id del vehiculo a eliminar
     * @return void
     */
    public void borrarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

    /**
     * Contar la cantidad de vehiculos para un modelo
     *
     * @param Long id del modelo
     * @return int Cantidad de vehiculos
     */
    public int countVehiculosByModelo(Long id) {
        return vehiculoRepository.countVehiculosByModelo_Id(id);
    }

}
