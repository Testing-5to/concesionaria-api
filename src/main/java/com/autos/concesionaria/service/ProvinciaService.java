package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinciaService {

    // Repositorio de Provincia
    private final ProvinciaRepository provinciaRepository;

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
     * @param pais Nombre del país
     * @return List<Provincia> Lista de provincias por país
     */
    public List<Provincia> buscarProvincias(String pais) {
        return provinciaRepository.findAllByPais_Nombre(pais);
    }

    /**
     * Método que devuelve una provincia por id
     *
     * @param id Id de la provincia
     * @return Provincia Provincia
     */
    public Provincia buscarProvinciaPorId(Long id) {
        return provinciaRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea una provincia
     *
     * @param provincia Provincia a crear
     * @return Provincia creada
     */
    public Provincia crearProvincia(Provincia provincia) {
        return provinciaRepository.save(provincia);
    }

    /**
     * Método que actualiza una provincia
     *
     * @param id Id de la provincia a actualizar
     * @param provincia Provincia a actualizar
     * @return Provincia actualizada
     */
    public Provincia actualizarProvinciaPorId(Long id, Provincia provincia) {
        provincia.setId(id);
        return provinciaRepository.saveAndFlush(provincia);
    }

    /**
     * Método que elimina una provincia
     *
     * @param id Id de la provincia
     */
    public void eliminarProvinciaPorId(Long id) {
        provinciaRepository.deleteById(id);
    }

    /**
     * Método que cuenta las provincias para un país
     *
     * @param id Id del país al que se le cuentan las provincias
     * @return int Cantidad de provincias para el país
     */
    public int contarProvinciasPorPais(Long id) {
        return provinciaRepository.countByPais_Id(id);
    }
}