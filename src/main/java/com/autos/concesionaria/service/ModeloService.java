package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService {

    @Autowired
    // Repository injected by constructor
    private ModeloRepository modeloRepository;

    /**
     * Create a modelo
     *
     * @param modelo
     * @return Modelo created
     */
    public Modelo crearModelo(Modelo modelo) {
        Modelo nuevoModelo = modeloRepository.save(modelo);
        return nuevoModelo;
    }

    /**
     * Get all the modelos
     *
     * @return List<Modelo> List of modelos
     */
    public List<Modelo> getModelos() {
        return modeloRepository.findAll();
    }

    /**
     * Get all the modelos by marca
     *
     * @return List<Modelo> List of modelos
     */
    public List<Modelo> getModelosByMarca(String marca) {
        return modeloRepository.findAllByMarca_Nombre(marca);
    }

    /**
     * Get a modelo by id
     *
     * @param id
     * @return Modelo found or null
     */
    public Modelo getModelo(Long id) {
        return modeloRepository.findById(id).get();
    }

    /**
     * Update a modelo
     *
     * @param id     Modelo id
     * @param modelo Modelo data to update
     * @return Modelo updated
     */
    public Modelo actualizarModelo(Long id, Modelo modelo) {
        Modelo modeloActual = modeloRepository.findById(id).get();
        modeloActual.setNombre(modelo.getNombre());
        modeloActual.setMarca(modelo.getMarca());
        return modeloRepository.save(modeloActual);
    }

    /**
     * Delete a modelo
     *
     * @param id Modelo id
     */
    public void borrarModelo(Long id) {
        modeloRepository.deleteById(id);
    }

}
