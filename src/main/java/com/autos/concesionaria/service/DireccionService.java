package com.autos.concesionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.repository.DireccionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DireccionService {

    // Repository injected by constructor
    @Autowired
    private final DireccionRepository direccionRepository;

    /**
     * Create a new direccion
     *
     * @param direccion
     * @return Direccion created
     */
    public Direccion crearDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    /**
     * Get all the direccions
     *
     * @return List<Direccion> List of direccions
     */
    public List<Direccion> buscarDirecciones() {
        return direccionRepository.findAll();
    }

    /**
     * Get all the direccions by localidad
     *
     * @param localidad
     * @return List<Direccion> List of direccions
     */
    public List<Direccion> buscarDireccionesPorLocalidad(String localidad) {
        return direccionRepository.findAllByLocalidad_Nombre(localidad);
    }

    /**
     * Get a direccion by id
     *
     * @param id
     * @return Direccion found or null
     */
    public Direccion buscarDireccionPorId(Long id) {
        return direccionRepository.findById(id).get();
    }

    /**
     * Update a direccion
     *
     * @param id       Direccion id
     * @param direccion Direccion data to update
     * @return Direccion updated
     */
    public Direccion actualizarDireccionPorId(Long id, Direccion direccion) {
        Direccion direccionActual = direccionRepository.findById(id).get();
        direccionActual.setCalle(direccion.getCalle());
        direccionActual.setNumero(direccion.getNumero());
        direccionActual.setPiso(direccion.getPiso());
        direccionActual.setDepartamento(direccion.getDepartamento());
        return direccionRepository.save(direccionActual);
    }

    /**
     * Delete a direccion
     *
     * @param id Direccion id
     */
    public void eliminarDireccionPorId(Long id) {
        direccionRepository.deleteById(id);
    }

    /**
     * Buscar si existe una direccion
     *
     * @param calle
     * @param numero
     * @param piso
     * @param departamento
     * @return boolean true if exists, false if not
     */

    public boolean existeDireccion(Direccion direccion) {
        return direccionRepository.existsByCalleAndNumeroAndPisoAndDepartamentoAndLocalidad_Id(
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getPiso(),
                direccion.getDepartamento(),
                direccion.getLocalidad().getId());
    }

    /**
     * Buscar direccion por calle, numero, piso, departamento y localidad
     *
     * @param calle
     * @param numero
     * @param piso
     * @param departamento
     * @param localidad
     * @return Long id
     */

    public Direccion buscarDireccion(Direccion direccion) {
        return direccionRepository.findByCalleAndNumeroAndPisoAndDepartamentoAndLocalidad_Id(
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getPiso(),
                direccion.getDepartamento(),
                direccion.getLocalidad().getId());
    }

    public int contarDireccionesPorLocalidad(Long id) {
        return direccionRepository.countByLocalidad_Id(id);
    }
}
