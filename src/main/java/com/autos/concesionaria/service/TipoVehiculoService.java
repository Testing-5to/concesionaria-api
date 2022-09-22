package com.autos.concesionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.repository.TipoVehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoVehiculoService {

    // Repository injected by constructor
    @Autowired
    private final TipoVehiculoRepository tipoVehiculoRepository;

    /**
     * Create a tipoVehiculo
     *
     * @param tipoVehiculo
     * @return TipoVehiculo created
     */
    public TipoVehiculo crearTipoVehiculo(TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    /**
     * Get all the tipoVehiculos
     *
     * @return List<TipoVehiculo> List of tipoVehiculos
     */
    public List<TipoVehiculo> buscarTipoVehiculos() {
        return tipoVehiculoRepository.findAll();
    }

    /**
     * Get a tipoVehiculo by id
     *
     * @param id
     * @return TipoVehiculo found or null
     */
    public TipoVehiculo buscarTipoVehiculoPorId(Long id) {
        return tipoVehiculoRepository.findById(id).get();
    }

    /**
     * Update a tipoVehiculo
     *
     * @param id           TipoVehiculo id
     * @param tipoVehiculo TipoVehiculo data to update
     * @return TipoVehiculo updated
     */
    public TipoVehiculo actualizarTipoVehiculoPorId(Long id, TipoVehiculo tipoVehiculo) {
        TipoVehiculo tipoVehiculoActual = tipoVehiculoRepository.findById(id).get();
        tipoVehiculoActual.setNombre(tipoVehiculo.getNombre());
        return tipoVehiculoRepository.saveAndFlush(tipoVehiculoActual);
    }

    /**
     * Delete a tipoVehiculo
     *
     * @param id TipoVehiculo id
     */
    public void eliminarTipoVehiculoPorId(Long id) {
        tipoVehiculoRepository.deleteById(id);
    }

}