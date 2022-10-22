package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Impuesto;
import com.autos.concesionaria.repository.ImpuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpuestoService {

    @Autowired
    // Inyección de dependencia por constructor
    private final ImpuestoRepository impuestoRepository;

    /**
     * Obtener todos los impuestos
     *
     * @return List<Impuesto> Lista de impuestos
     */
    public List<Impuesto> buscarImpuestos() {
        return impuestoRepository.findAll();
    }

}
