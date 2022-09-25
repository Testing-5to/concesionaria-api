package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Cliente;
import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.entity.Empleado;
import com.autos.concesionaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    // Repository injected by constructor
    private final ClienteRepository clienteRepository;

    /**
     * Create an employee
     *
     * @param cliente
     * @return Empleado created
     */
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Get all the employees
     *
     * @return List<Empleado> List of employees
     */
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Get an employee by id
     *
     * @param id
     * @return Empleado found or null
     */
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    /**
     * Update an employee
     *
     * @param id       Empleado id
     * @param cliente Empleado data to update
     * @return Empleado updated
     */
    public Cliente actualizarClientePorId(Long id, Cliente cliente) {
        Cliente clienteActual = clienteRepository.findById(id).get();
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setTelefono(cliente.getTelefono());
        clienteActual.setDni(cliente.getDni());
        clienteActual.setEmail(cliente.getEmail());
        clienteActual.setEsCliente(cliente.getEsCliente());
        clienteActual.setDireccion(cliente.getDireccion());
        return clienteRepository.saveAndFlush(clienteActual);
    }

    /**
     * Delete an employee
     *
     * @param id Empleado id
     */
    public void eliminarClientePorId(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Contrar clientes por id de direccion
     * @param direccionId
     * @return
     */
    public int contarClientesPorDireccion(Long direccionId) {
        return clienteRepository.countAllByDireccion_Id(direccionId);
    }
}
