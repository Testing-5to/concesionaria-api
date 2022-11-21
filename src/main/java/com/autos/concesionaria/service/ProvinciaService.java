package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinciaService {

    // Repositorio de Provincia
    @Autowired
    private ProvinciaRepository provinciaRepository;

    /**
     * Método que devuelve todas las provincias
     *
     * @return List<Provincia> Lista de provincias
     */
    public List<Provincia> buscarProvincias() {
        return provinciaRepository.findAll();
    }

    /**
     * Método que devuelve las provincias por país
     *
     * @param String pais Nombre del país
     * @return List<Provincia> Lista de provincias por país
     */
    public List<Provincia> buscarProvinciasByPais(String pais) {
        return provinciaRepository.findAllByPais_Nombre(pais);
    }

    /**
     * Método que devuelve una provincia por id
     *
     * @param Long id Id de la provincia
     * @return Provincia Provincia
     */
    public Provincia buscarProvinciaPorId(Long id) {
        return provinciaRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea una provincia
     *
     * @param Provincia provincia Provincia
     * @return Provincia creada
     */
    public Provincia crearProvincia(Provincia provincia) {
        return provinciaRepository.save(provincia);
    }

    /**
     * Método que actualiza una provincia
     *
     * @param Long      id Id de la provincia
     * @param Provincia provincia Provincia
     * @return Provincia actualizada
     */
    public Provincia actualizarProvinciaPorId(Long id, Provincia provincia) {
        Provincia provinciaActual = provinciaRepository.findById(id).orElse(null);
        provinciaActual.setNombre(provincia.getNombre());
        provinciaActual.setPais(provincia.getPais());
        return provinciaRepository.saveAndFlush(provinciaActual);
    }

    /**
     * Método que elimina una provincia
     *
     * @param Long id Id de la provincia
     * @return Provincia eliminada
     */
    public void eliminarProvinciaPorId(Long id) {
        provinciaRepository.deleteById(id);
    }

    /**
     * Método que cuenta las provincias para un país
     *
     * @param Long id Id del país
     * @return int Cantidad de provincias para el país
     */
    public int contarProvinciasPorPais(Long id) {
        return provinciaRepository.countByPais_Id(id);
    }
}