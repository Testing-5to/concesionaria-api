package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Cliente;
import com.autos.concesionaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    // Repositorio de Cliente
    @Autowired
    private final ClienteRepository clienteRepository;

    /**
     * Método que permite guardar un cliente en la base de datos
     *
     * @param Cliente cliente cliente a guardar
     * @return Cliente guardado
     */
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Método que permite obtener todos los clientes de la base de datos
     *
     * @return List<Cliente> de clientes de la base de datos
     */
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Método que permite obtener un cliente por su id
     *
     * @param Long id del cliente a buscar
     * @return Cliente encontrado o null si no existe
     */
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    /**
     * Método que permite actualizar un cliente
     *
     * @param Cliente cliente a actualizar
     * @param Long    id del cliente a actualizar
     * @return Cliente actualizado
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
     * Método que permite eliminar un cliente por su id
     *
     * @param Long id del cliente a eliminar
     * @return void
     */
    public void eliminarClientePorId(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Contrar clientes por id de direccion
     *
     * @param direccionId id de la direccion
     * @return int cantidad de clientes con esa direccion
     */
    public int contarClientesPorDireccion(Long direccionId) {
        return clienteRepository.countAllByDireccion_Id(direccionId);
    }
}
