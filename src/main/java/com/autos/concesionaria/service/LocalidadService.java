package com.autos.concesionaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Localidad;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalidadService {

    @Autowired
    // Repository injected by constructor
    private LocalidadService localidadService;

    /**
     * Create a localidad
     *
     * @param localidad
     * @return Localidad created
     */
    public Localidad crearLocalidad(Localidad localidad) {
        return localidadService.crearLocalidad(localidad);
    }

    /**
     * Get all the localidades
     *
     * @return List<Localidad> List of localidades
     */
    public List<Localidad> buscarLocalidades() {
        return localidadService.buscarLocalidades();
    }

    /**
     * Get a localidad by id
     *
     * @param id
     * @return Localidad found or null
     */
    public Localidad buscarLocalidadPorId(Long id) {
        return localidadService.buscarLocalidadPorId(id);
    }

    /**
     * Update a localidad
     *
     * @param id        Localidad id
     * @param localidad Localidad data to update
     * @return Localidad updated
     */
    public Localidad actualizarLocalidadPorId(Long id, Localidad localidad) {
        Localidad localidadActual = localidadService.buscarLocalidadPorId(id);
        localidadActual.setNombre(localidad.getNombre());
        localidadActual.setCodigoPostal(localidad.getCodigoPostal());
        localidadActual.setProvincia(localidad.getProvincia());
        return localidadService.crearLocalidad(localidadActual);
    }

    /**
     * Delete a localidad
     *
     * @param id Localidad id
     */
    public void eliminarLocalidadPorId(Long id) {
        localidadService.eliminarLocalidadPorId(id);
    }

}