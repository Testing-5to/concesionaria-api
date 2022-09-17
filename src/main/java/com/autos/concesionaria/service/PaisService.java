package com.autos.concesionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.repository.PaisRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> buscarPaises() {
        return paisRepository.findAll();
    }

    public Pais buscarPaisPorId(Long id) {
        return paisRepository.findById(id).get();
    }

    public Pais crearPais(Pais pais) {
        return paisRepository.save(pais);
    }

    public Pais actualizarPaisPorId(Long id, Pais pais) {
        pais.setId(id);
        return paisRepository.save(pais);
    }

    public void borrarPaisPorId(Long id) {
        paisRepository.deleteById(id);
    }

}