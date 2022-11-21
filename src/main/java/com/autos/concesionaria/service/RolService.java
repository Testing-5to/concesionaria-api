package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {

    // Repositorio de Rol
    @Autowired
    private final RolRepository rolRepository;

    /**
     * Método que devuelve todos los roles
     *
     * @return List<Rol> Lista de roles
     */
    public List<Rol> buscarRoles() {
        return rolRepository.findAll();
    }

    /**
     * Método que devuelve un rol por su id
     *
     * @param Long id del rol
     * @return Rol encontrado
     */
    public Rol buscarRolPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea un rol
     *
     * @param Rol rol a crear
     * @return Rol creado
     */
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    /**
     * Método que actualiza un rol
     *
     * @param Long id del rol a actualizar
     * @param Rol  rol a actualizar
     * @return Rol actualizado
     */
    public Rol actualizarRolPorId(Long id, Rol rol) {
        Rol rolActual = rolRepository.findById(id).orElse(null);
        rolActual.setNombre(rol.getNombre());
        return rolRepository.saveAndFlush(rolActual);
    }

    /**
     * Método que elimina un rol
     *
     * @param Long id del rol a eliminar
     * @return void
     */
    public void eliminarRolPorId(Long id) {
        rolRepository.deleteById(id);
    }

}
