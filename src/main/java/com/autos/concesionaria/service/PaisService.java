package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {

    // Repositorio de Pais
    @Autowired
    private PaisRepository paisRepository;

    /**
     * Método que devuelve todos los paises
     *
     * @return List<Pais> Lista de paises
     */
    public List<Pais> buscarPaises() {
        return paisRepository.findAll();
    }

    /**
     * Método que devuelve un pais por su id
     *
     * @param Long id del pais
     * @return Pais encontrado
     */
    public Pais buscarPaisPorId(Long id) {
        return paisRepository.findById(id).get();
    }

    /**
     * Método que crea un pais
     *
     * @param Pais pais a crear
     * @return Pais creado
     */
    public Pais crearPais(Pais pais) {
        return paisRepository.save(pais);
    }

    /**
     * Método que actualiza un pais
     *
     * @param Long id del pais a actualizar
     * @param Pais pais a actualizar
     * @return Pais actualizado
     */
    public Pais actualizarPaisPorId(Long id, Pais pais) {
        Pais paisActual = paisRepository.findById(id).get();
        paisActual.setNombre(pais.getNombre());
        paisActual.setAbreviatura(pais.getAbreviatura());
        return paisRepository.saveAndFlush(paisActual);
    }

    /**
     * Método que elimina un pais
     *
     * @param Long id del pais a eliminar
     * @return void
     */
    public void borrarPaisPorId(Long id) {
        paisRepository.deleteById(id);
    }

}
