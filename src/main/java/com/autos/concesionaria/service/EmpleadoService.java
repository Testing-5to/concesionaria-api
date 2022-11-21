package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Empleado;
import com.autos.concesionaria.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    // Repositorio de Empleado
    @Autowired
    private final EmpleadoRepository empleadoRepository;

    /**
     * Método que obtiene todos los empleados
     *
     * @return List<Empleado> Lista de empleados
     */
    public List<Empleado> buscarEmpleados() {
        return empleadoRepository.findAll();
    }

    /**
     * Método que obtiene los empleados para un rol
     *
     * @param rol Rol del empleado
     * @return List<Empleado> Lista de empleados para el rol
     */
    public List<Empleado> buscarEmpleados(String rol) {
        return empleadoRepository.findAllByRol_Nombre(rol);
    }

    /**
     * Método que obtiene un empleado por su id
     *
     * @param id Id del empleado
     * @return Empleado con el id indicado
     */
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea un empleado
     *
     * @param empleado a crear
     * @return Empleado creado
     */
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /**
     * Método que actualiza un empleado
     *
     * @param Long     id del empleado a actualizar
     * @param Empleado empleado a actualizar
     * @return Empleado actualizado
     */
    public Empleado actualizarEmpleadoPorId(Long id, Empleado empleado) {
        Empleado empleadoActual = empleadoRepository.findById(id).orElse(null);
        empleadoActual.setNombre(empleado.getNombre());
        empleadoActual.setApellido(empleado.getApellido());
        empleadoActual.setTelefono(empleado.getTelefono());
        empleadoActual.setDni(empleado.getDni());
        empleadoActual.setEmail(empleado.getEmail());
        empleadoActual.setFechaIngreso(empleado.getFechaIngreso());
        empleadoActual.setFechaEgreso(empleado.getFechaEgreso());
        empleadoActual.setSalario(empleado.getSalario());
        empleadoActual.setRol(empleado.getRol());
        empleadoActual.setDireccion(empleado.getDireccion());
        return empleadoRepository.saveAndFlush(empleadoActual);
    }

    /**
     * Método que elimina un empleado por su id
     *
     * @param id Id del empleado a eliminar
     * @return void
     */
    public void eliminarEmpleadoPorId(Long id) {
        empleadoRepository.deleteById(id);
    }

    /**
     * Contar empleados para una dirección
     *
     * @param Long idDireccion Id de la dirección
     * @return int Cantidad de empleados para la dirección
     */
    public int contarEmpleadosPorDireccion(Long idDireccion) {
        return empleadoRepository.countByDireccion_Id(idDireccion);
    }

    /**
     * Contar empleados para un rol
     *
     * @param id Id del rol
     * @return int Cantidad de empleados para el rol
     */
    public int contarEmpleadosPorRol(Long id) {
        return empleadoRepository.countByRol_Id(id);
    }

}
