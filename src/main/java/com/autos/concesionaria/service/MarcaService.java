package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

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

    public Marca borrarMarca(Long id) {
        try {
            Marca marca = marcaRepository.findById(id).get();
            marcaRepository.deleteById(id);
            return marca;
        } catch (Exception e) {
            return null;
        }
    }

}
