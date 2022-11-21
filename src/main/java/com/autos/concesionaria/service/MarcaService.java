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

    // Repositorio de Marca
    @Autowired
    private MarcaRepository marcaRepository;


    /**
     * Método que devuelve todas las marcas
     *
     * @return List<Marca> lista de marcas
     */
    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    /**
     * Método que devuelve una marca para un pais
     *
     * @param String nombre del pais
     * @return List<Marca> lista de marcas para un pais
     */
    public List<Marca> getMarcasByPais(String nombre) {
        return marcaRepository.findAllByPais_Nombre(nombre);
    }

    /**
     * Método que devuelve una marca por su id
     *
     * @param Long id de la marca
     * @return Marca marca con el id
     */
    public Marca getMarca(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea una marca
     *
     * @param Marca marca a crear
     * @return Marca marca creada
     */
    public Marca crearMarca(Marca marca) {
        Marca nuevaMarca = marcaRepository.save(marca);
        return nuevaMarca;
    }

    /**
     * Método que actualiza una marca
     *
     * @param Long  id de la marca
     * @param Marca marca a actualizar
     * @return Marca marca actualizada
     */
    public Marca actualizarMarca(Long id, Marca marca) {
        Marca marcaActual = marcaRepository.findById(id).orElse(null);
        marcaActual.setNombre(marca.getNombre());
        marcaActual.setPais(marca.getPais());
        return marcaRepository.save(marcaActual);
    }

    /**
     * Método que elimina una marca
     *
     * @param Long id de la marca
     * @return void
     */
    public void borrarMarca(Long id) {
        marcaRepository.deleteById(id);
    }

    /**
     * Método que cuenta las marcas de un pais
     *
     * @param Long id del pais
     * @return int cantidad de marcas del pais
     */
    public int contarMarcasPorPais(Long id) {
        return marcaRepository.countByPais_Id(id);
    }

}
