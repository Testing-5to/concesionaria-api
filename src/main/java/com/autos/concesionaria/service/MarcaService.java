package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca crearMarca(Marca marca) {
        Marca nuevaMarca = marcaRepository.save(marca);
        return nuevaMarca;
    }

    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    public Marca getMarca(Long id) {
        return marcaRepository.findById(id).get();
    }

    public Marca actualizarMarca(Long id, Marca marca) {
        marca.setId(id);
        return marcaRepository.save(marca);
    }

    public void borrarMarca(Long id) {
        marcaRepository.deleteById(id);
    }

}
