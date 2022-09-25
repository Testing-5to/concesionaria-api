package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Cliente;
import com.autos.concesionaria.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ClienteController {

    // Service injected by constructor
    @Autowired
    private final ClienteService clienteService;

    // GET
    // Get mapping to get all the employees
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return new ResponseEntity<>(clienteService.buscarClientes(), HttpStatus.OK);
    }

    // GET by ID
    // Get mapping to get an employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.buscarClientePorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create an employee
    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crearCliente(cliente), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.actualizarClientePorId(id, cliente), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarClientePorId(id);
        return new ResponseEntity<String>("Cliente eliminado", HttpStatus.OK);
    }

}
