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

    @Autowired
    // Repository injected by constructor
    private final EmpleadoRepository empleadoRepository;

    /**
     * Create an employee
     *
     * @param empleado
     * @return Empleado created
     */
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /**
     * Get all the employees
     *
     * @return List<Empleado> List of employees
     */
    public List<Empleado> buscarEmpleados() {
        return empleadoRepository.findAll();
    }

    /**
     * Get all the employees by rol
     *
     * @return List<Empleado> List of employees
     */
    public List<Empleado> buscarEmpleadosByRol(String rol) {
        return empleadoRepository.findAllByRol_Nombre(rol);
    }

    /**
     * Get an employee by id
     *
     * @param id
     * @return Empleado found or null
     */
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).get();
    }

    /**
     * Update an employee
     *
     * @param id       Empleado id
     * @param empleado Empleado data to update
     * @return Empleado updated
     */
    public Empleado actualizarEmpleadoPorId(Long id, Empleado empleado) {
        Empleado empleadoActual = empleadoRepository.findById(id).get();
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
     * Delete an employee
     *
     * @param id Empleado id
     */
    public void eliminarEmpleadoPorId(Long id) {
        empleadoRepository.deleteById(id);
    }

    /**
     * Contar empleados por id de direccion
     *
     * @param id
     * @return Long cantidad de empleados
     */
    public int contarEmpleadosPorDireccion(Long idDireccion) {
        return empleadoRepository.countByDireccion_Id(idDireccion);
    }

}
