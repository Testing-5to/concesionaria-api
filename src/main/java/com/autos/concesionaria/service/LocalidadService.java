package com.autos.concesionaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Localidad;
import com.autos.concesionaria.repository.LocalidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalidadService {

    @Autowired
    // Repository injected by constructor
    private LocalidadRepository localidadRepository;

    /**
     * Create a new localidad
     *
     * @param localidad
     * @return Localidad created
     */
    public Localidad crearLocalidad(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    /**
     * Get all the localidades
     *
     * @return List<Localidad> List of localidades
     */
    public List<Localidad> buscarLocalidades() {
        return localidadRepository.findAll();
    }

    /**
     * Get a localidad by id
     *
     * @param id
     * @return Localidad found or null
     */
    public Localidad buscarLocalidadPorId(Long id) {
        return localidadRepository.findById(id).get();
    }

    /**
     * Update a localidad
     *
     * @param id        Localidad id
     * @param localidad Localidad data to update
     * @return Localidad updated
     */
    public Localidad actualizarLocalidadPorId(Long id, Localidad localidad) {
        Localidad localidadActual = localidadRepository.findById(id).get();
        localidadActual.setNombre(localidad.getNombre());
        localidadActual.setCodigoPostal(localidad.getCodigoPostal());
        localidadActual.setProvincia(localidad.getProvincia());
        return localidadRepository.saveAndFlush(localidadActual);
    }

    /**
     * Delete a localidad
     *
     * @param id Localidad id
     */
    public void eliminarLocalidadPorId(Long id) {
        localidadRepository.deleteById(id);
    }

}