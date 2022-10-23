package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.repository.DireccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionService {

    // Repositorio de Direccion
    @Autowired
    private final DireccionRepository direccionRepository;

    /**
     * Método que obtiene todas las direcciones
     *
     * @return List<Direccion> Lista de direcciones
     */
    public List<Direccion> buscarDirecciones() {
        return direccionRepository.findAll();
    }

    /**
     * Método que obtiene las direcciones para una localidad
     *
     * @param localidad Localidad a buscar
     * @return List<Direccion> Lista de direcciones para la localidad
     */
    public List<Direccion> buscarDireccionesPorLocalidad(String localidad) {
        return direccionRepository.findAllByLocalidad_Nombre(localidad);
    }

    /**
     * Método que obtiene las direcciones para un id
     *
     * @param id Id de la dirección
     * @return Direccion Dirección encontrada
     */
    public Direccion buscarDireccionPorId(Long id) {
        return direccionRepository.findById(id).get();
    }

    /**
     * Método que crea una nueva dirección
     *
     * @param direccion Direccion a crear
     * @return Direccion creada
     */
    public Direccion crearDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    /**
     * Método que actualiza una dirección
     *
     * @param id        Id de la dirección
     * @param direccion Direccion a actualizar
     * @return Direccion actualizada
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
     * Método que elimina una dirección por id
     *
     * @param id Id de la dirección
     * @return void
     */
    public void eliminarDireccionPorId(Long id) {
        direccionRepository.deleteById(id);
    }

    /**
     * Buscar si existe una direccion
     *
     * @param Direccion direccion a buscar
     * @return boolean true si existe, false si no existe
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
     * Buscar si existe una direccion y retornarla
     *
     * @param Direccion direccion a buscar
     * @return Direccion direccion encontrada
     */
    public Direccion buscarDireccion(Direccion direccion) {
        return direccionRepository.findByCalleAndNumeroAndPisoAndDepartamentoAndLocalidad_Id(
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getPiso(),
                direccion.getDepartamento(),
                direccion.getLocalidad().getId());
    }

    /**
     * Contar la cantidad de direcciones para una localidad
     *
     * @param id Id de la dirección
     * @return int Cantidad de direcciones para la localidad
     */
    public int contarDireccionesPorLocalidad(Long id) {
        return direccionRepository.countByLocalidad_Id(id);
    }
}
