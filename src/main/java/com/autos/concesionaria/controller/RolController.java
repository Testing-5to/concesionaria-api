package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.service.EmpleadoService;
import com.autos.concesionaria.service.RolService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rol")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class RolController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(RolController.class);

    // Inyección de dependencias
    @Autowired
    private final RolService rolService;
    @Autowired
    private final EmpleadoService empleadoService;

    // GET
    // Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Rol>> getRoles() {
        return new ResponseEntity<>(rolService.buscarRoles(), HttpStatus.OK);
    }

    // GET by ID
    // Obtener un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolPorId(@PathVariable Long id) {
        return new ResponseEntity<>(rolService.buscarRolPorId(id), HttpStatus.OK);
    }

    // POST
    // Crear un nuevo rol
    @PostMapping
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol) {
        logger.info("Guardando rol: " + rol);
        return new ResponseEntity<>(rolService.crearRol(rol), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar un rol
    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        logger.info("Actualizando rol: " + rol);
        return new ResponseEntity<>(rolService.actualizarRolPorId(id, rol), HttpStatus.OK);
    }

    // DELETE
    // Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Long id) {
        // Verifico que el rol no este asignado a un empleado
        if (empleadoService.contarEmpleadosPorRol(id) > 0) {
            rolService.eliminarRolPorId(id);
            logger.info("Eliminando rol por id: " + id);
            return new ResponseEntity<String>("Rol borrado: " + id, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("El rol no puede ser eliminado porque esta asignado a un empleado", HttpStatus.BAD_REQUEST);
        }
    }

}