package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Localidad;
import com.autos.concesionaria.repository.LocalidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalidadService {

    // Repositorio de Localidad
    private LocalidadRepository localidadRepository;

    public LocalidadService(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    /**
     * Obtiene todas las localidades
     *
     * @return List<Localidad> Lista de localidades
     */
    public List<Localidad> buscarLocalidades() {
        return localidadRepository.findAll();
    }

    /**
     * Obtiene las localidades para una provincia
     *
     * @param provincia Nombre de la provincia
     * @return List<Localidad> Lista de localidades
     */
    public List<Localidad> buscarLocalidades(String provincia) {
        return localidadRepository.findAllByProvincia_Nombre(provincia);
    }

    /**
     * Obtiene una localidad por su id
     *
     * @param id Id de la localidad
     * @return Localidad Localidad
     */
    public Localidad buscarLocalidad(Long id) {
        return localidadRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea una nueva Localidad
     *
     * @param localidad localidad a crear
     * @return Localidad creada
     */
    public Localidad crearLocalidad(Localidad localidad) {
        return localidadRepository.saveAndFlush(localidad);
    }

    /**
     * Método que actualiza una Localidad
     *
     * @param id de la localidad a actualizar
     * @param localidad a actualizar
     * @return Localidad actualizada
     */
    public Localidad actualizarLocalidad(Long id, Localidad localidad) {
        localidad.setId(id);
        return localidadRepository.saveAndFlush(localidad);
    }

    /**
     * Método que elimina una Localidad
     *
     * @param id de la localidad a eliminar
     */
    public void eliminarLocalidad(Long id) {
        localidadRepository.deleteById(id);
    }

    /**
     * Método que cuenta la cantidad de Localidades para una determinada Provincia
     *
     * @param idProvincia de la provincia
     * @return int cantidad de localidades para una provincia
     */
    public int contarLocalidadesPorProvincia(Long idProvincia) {
        return localidadRepository.countByProvincia_Id(idProvincia);
    }
}