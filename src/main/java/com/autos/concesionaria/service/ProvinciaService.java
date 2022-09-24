package com.autos.concesionaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinciaService {

    @Autowired
    // Repository injected by constructor
    private ProvinciaRepository provinciaRepository;

    /**
     * Create a provincia
     *
     * @param pais
     * @return Pais created
     */
    public Provincia crearProvincia(Provincia provincia) {
        return provinciaRepository.save(provincia);
    }

    /**
     * Get all the provincias
     *
     * @return List<Provincia> List of provincias
     */
    public List<Provincia> buscarProvincias() {
        return provinciaRepository.findAll();
    }

    /**
     * Get all the provincias by pais
     *
     * @return List<Provincia> List of provincias by pais
     */
    public List<Provincia> buscarProvinciasByPais(String pais) {
        return provinciaRepository.findAllByPais_Nombre(pais);
    }

    /**
     * Get a provincia by id
     *
     * @param id
     * @return Provincia found or null
     */
    public Provincia buscarProvinciaPorId(Long id) {
        return provinciaRepository.findById(id).get();
    }

    /**
     * Update a provincia
     *
     * @param id   Provincia id
     * @param pais Provincia data to update
     * @return Provincia updated
     */
    public Provincia actualizarProvinciaPorId(Long id, Provincia provincia) {
        Provincia provinciaActual = provinciaRepository.findById(id).get();
        provinciaActual.setNombre(provincia.getNombre());
        provinciaActual.setPais(provincia.getPais());
        return provinciaRepository.saveAndFlush(provinciaActual);
    }

    /**
     * Delete a provincia
     *
     * @param id Provincia id
     */
    public void eliminarProvinciaPorId(Long id) {
        provinciaRepository.deleteById(id);
    }

}