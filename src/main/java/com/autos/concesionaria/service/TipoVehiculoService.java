package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.repository.TipoVehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoVehiculoService {

    // Repositorio de tipo de vehiculo
    @Autowired
    private final TipoVehiculoRepository tipoVehiculoRepository;

    /**
     * Obtiene todos los tipos de vehiculos
     *
     * @return List<TipoVehiculo> Lista de tipos de vehiculos
     */
    public List<TipoVehiculo> buscarTipoVehiculos() {
        return tipoVehiculoRepository.findAll();
    }

    /**
     * Obtiene un tipo de vehiculo por su id
     *
     * @param Long id del tipo de vehiculo
     * @return TipoVehiculo Tipo de vehiculo encontrado
     */
    public TipoVehiculo buscarTipoVehiculoPorId(Long id) {
        return tipoVehiculoRepository.findById(id).orElse(null);
    }

    /**
     * Metodo para crear un tipo de vehiculo
     *
     * @param TipoVehiculo tipoVehiculo a crear
     * @return TipoVehiculo creado
     */
    public TipoVehiculo crearTipoVehiculo(TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    /**
     * Metodo para actualizar un tipo de vehiculo
     *
     * @param Long         id del tipo de vehiculo a actualizar
     * @param TipoVehiculo tipoVehiculo a actualizar
     * @return TipoVehiculo actualizado
     */
    public TipoVehiculo actualizarTipoVehiculoPorId(Long id, TipoVehiculo tipoVehiculo) {
        TipoVehiculo tipoVehiculoActual = tipoVehiculoRepository.findById(id).orElse(null);
        tipoVehiculoActual.setNombre(tipoVehiculo.getNombre());
        return tipoVehiculoRepository.saveAndFlush(tipoVehiculoActual);
    }

    /**
     * Metodo para eliminar un tipo de vehiculo
     *
     * @param Long id del tipo de vehiculo a eliminar
     * @return void
     */
    public void eliminarTipoVehiculoPorId(Long id) {
        tipoVehiculoRepository.deleteById(id);
    }

}
