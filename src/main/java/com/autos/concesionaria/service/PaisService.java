package com.autos.concesionaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.repository.PaisRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaisService {

    @Autowired
    // Repository injected by constructor
    private PaisRepository paisRepository;

    /**
     * Create a pais
     *
     * @param pais
     * @return Pais created
     */
    public Pais crearPais(Pais pais) {
        return paisRepository.save(pais);
    }

    /**
     * Get all the paises
     *
     * @return List<Pais> List of paises
     */
    public List<Pais> buscarPaises() {
        return paisRepository.findAll();
    }

    /**
     * Get a pais by id
     *
     * @param id
     * @return Pais found or null
     */
    public Pais buscarPaisPorId(Long id) {
        return paisRepository.findById(id).get();
    }

    /**
     * Update a pais
     *
     * @param id   Pais id
     * @param pais Pais data to update
     * @return Pais updated
     */
    public Pais actualizarPaisPorId(Long id, Pais pais) {
        Pais paisActual = paisRepository.findById(id).get();
        paisActual.setNombre(pais.getNombre());
        paisActual.setAbreviatura(pais.getAbreviatura());
        return paisRepository.saveAndFlush(paisActual);
    }

    /**
     * Delete a pais
     *
     * @param id Pais id
     */
    public void borrarPaisPorId(Long id) {
        paisRepository.deleteById(id);
    }

}