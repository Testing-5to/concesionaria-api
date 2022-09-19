package com.autos.concesionaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Calle;
import com.autos.concesionaria.repository.CalleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalleService {

    // Repository injected by constructor
    @Autowired
    private CalleRepository calleRepository;

    /**
     * Create a new calle
     *
     * @param calle
     * @return Calle created
     */
    public Calle crearCalle(Calle calle) {
        return calleRepository.save(calle);
    }

    /**
     * Get all the calles
     *
     * @return List<Calle> List of calles
     */
    public List<Calle> buscarCalles() {
        return calleRepository.findAll();
    }

    /**
     * Get a calle by id
     *
     * @param id
     * @return Calle found or null
     */
    public Calle buscarCallePorId(Long id) {
        return calleRepository.findById(id).get();
    }

    /**
     * Update a calle
     *
     * @param id    Calle id
     * @param calle Calle data to update
     * @return Calle updated
     */
    public Calle actualizarCallePorId(Long id, Calle calle) {
        Calle calleActual = calleRepository.findById(id).get();
        calleActual.setNombre(calle.getNombre());
        calleActual.setLocalidad(calle.getLocalidad());
        return calleRepository.save(calleActual);
    }

    /**
     * Delete a calle
     *
     * @param id Calle id
     */
    public void eliminarCallePorId(Long id) {
        calleRepository.deleteById(id);
    }

}
