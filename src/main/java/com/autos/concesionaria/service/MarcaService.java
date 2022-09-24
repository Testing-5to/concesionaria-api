package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    @Autowired
    // Repository injected by constructor
    private MarcaRepository marcaRepository;

    /**
     * Create a marca
     *
     * @param marca
     * @return Marca created
     */
    public Marca crearMarca(Marca marca) {
        Marca nuevaMarca = marcaRepository.save(marca);
        return nuevaMarca;
    }

    /**
     * Get all the marcas
     *
     * @return List<Marca> List of marcas
     */
    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    /**
     * Get all the marcas by pais
     *
     * @return List<Marca> List of marcas
     */
    public List<Marca> getMarcasByPais(String nombre) {
        return marcaRepository.findAllByPais_Nombre(nombre);
    }

    /**
     * Get a marca by id
     *
     * @param id
     * @return Marca found or null
     */
    public Marca getMarca(Long id) {
        return marcaRepository.findById(id).get();
    }

    /**
     * Update a marca
     *
     * @param id    Marca id
     * @param marca Marca data to update
     * @return Marca updated
     */
    public Marca actualizarMarca(Long id, Marca marca) {
        Marca marcaActual = marcaRepository.findById(id).get();
        marcaActual.setNombre(marca.getNombre());
        marcaActual.setPais(marca.getPais());
        return marcaRepository.save(marcaActual);
    }

    /**
     * Delete a marca
     *
     * @param id Marca id to delete
     */
    public void borrarMarca(Long id) {
        marcaRepository.deleteById(id);
    }

}
