package com.autos.concesionaria.service;

import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.repository.PaisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaisService {

    private PaisRepository paisRepository;

    public Pais crearPais(Pais pais) {
        return paisRepository.save(pais);
    }

    public Pais buscarPaisPorId(Long id) {
        return paisRepository.findById(id).get();
    }

    public Pais actualizarPaisPorId(Long id, Pais pais) {
        pais.setId(id);
        return paisRepository.save(pais);
    }

    public Pais borrarPaisPorId(Long id) {
        Pais pais = paisRepository.findById(id).get();
        paisRepository.delete(pais);
        return pais;
    }

}
