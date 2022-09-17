package com.autos.concesionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {

    @Autowired
    // Repository injected by constructor
    private final RolRepository rolRepository;

    /**
     * Create a rol
     * @param rol
     * @return Rol created
    */
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    /**
     * Get all the roles
     * @return List<Rol> List of roles
    */
    public List<Rol> buscarRoles() {
        return rolRepository.findAll();
    }

    /**
     * Get a rol by id
     * @param id
     * @return Rol found or null
    */
    public Rol buscarRolPorId(Long id) {
        return rolRepository.findById(id).get();
    }

    /**
     * Update a rol
     * @param id Rol id
     * @param rol Rol data to update
     * @return Rol updated
    */
    public Rol actualizarRolPorId(Long id, Rol rol) {
        Rol rolActual = rolRepository.findById(id).get();
        rolActual.setNombre(rol.getNombre());
        return rolRepository.saveAndFlush(rolActual);
    }

    /**
     * Delete a rol
     * @param id Rol id
    */
    public void eliminarRolPorId(Long id) {
        rolRepository.deleteById(id);
    }

}
