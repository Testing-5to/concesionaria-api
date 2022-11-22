package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Localidad;
import com.autos.concesionaria.repository.LocalidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalidadService {

    // Repositorio de Localidad
    @Autowired
    private LocalidadRepository localidadRepository;

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
     * @param idProvincia Id de la provincia
     * @return List<Localidad> Lista de localidades
     */
    public List<Localidad> buscarLocalidadesPorProvincia(String provincia) {
        return localidadRepository.findAllByProvincia_Nombre(provincia);
    }

    /**
     * Obtiene una localidad por su id
     *
     * @param id Id de la localidad
     * @return Localidad Localidad
     */
    public Localidad buscarLocalidadPorId(Long id) {
        return localidadRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea una nueva Localidad
     *
     * @param Localidad localidad a crear
     * @return Localidad creada
     */
    public Localidad crearLocalidad(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    /**
     * Método que actualiza una Localidad
     *
     * @param Long      id de la localidad a actualizar
     * @param Localidad localidad a actualizar
     * @return Localidad actualizada
     */
    public Localidad actualizarLocalidadPorId(Long id, Localidad localidad) {
        Localidad localidadActual = localidadRepository.findById(id).orElse(null);
        localidadActual.setNombre(localidad.getNombre());
        localidadActual.setCodigoPostal(localidad.getCodigoPostal());
        localidadActual.setProvincia(localidad.getProvincia());
        return localidadRepository.saveAndFlush(localidadActual);
    }

    /**
     * Método que elimina una Localidad
     *
     * @param Long id de la localidad a eliminar
     * @return Localidad eliminada
     */
    public void eliminarLocalidadPorId(Long id) {
        localidadRepository.deleteById(id);
    }

    /**
     * Método que cuenta la cantidad de Localidades para una determinada Provincia
     *
     * @param Long id de la provincia
     * @return int cantidad de localidades para una provincia
     */
    public int contarLocalidadesPorProvincia(Long id) {
        return localidadRepository.countByProvincia_Id(id);
    }
}