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

    // Get all the localidades
    public List<Localidad> buscarLocalidades() {
        return localidadRepository.findAll();
    }

    // Get a localidad by id
    public Localidad buscarLocalidadPorId(Long id) {
        return localidadRepository.findById(id).orElseThrow();
    }

    // Create a localidad
    public Localidad crearLocalidad(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    // Update a localidad
    public Localidad actualizarLocalidadPorId(Long id, Localidad localidad) {
        Localidad localidadActual = localidadRepository.findById(id).orElseThrow();
        localidadActual.setNombre(localidad.getNombre());
        return localidadRepository.save(localidadActual);
    }

    // Delete a localidad
    public void eliminarLocalidadPorId(Long id) {
        localidadRepository.deleteById(id);
    }

}