package com.autos.concesionaria.service;

import com.autos.concesionaria.dto.MarcaDTO;
import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.repository.MarcaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModelMapper mapper;

    public MarcaDTO crearMarca(MarcaDTO marcaDTO){
        //mapeamos del DTO a entidad para poder guardar en base de datos
        Marca marca = mapper.map(marcaDTO, Marca.class);
        //Guardamos en base de datos el objeto marca
        Marca nuevaMarca = marcaRepository.save(marca);

        //pasamos el objeto marca de entidad a JSON para poder mostrarlo
        MarcaDTO marcaRespuesta = mapper.map(nuevaMarca, MarcaDTO.class);

        return marcaRespuesta;
    }

    public List<MarcaDTO> getMarcas(){

        List<Marca> marcas = marcaRepository.findAll();


        List<MarcaDTO> marcasDTO = mapper.map(marcas, List.class);

        return marcasDTO;


    }

    public MarcaDTO getMarca(Long id){
        Marca marca = marcaRepository.findById(id).get();

        MarcaDTO marcaDTO = mapper.map(marca, MarcaDTO.class);

        return marcaDTO;
    }

    public MarcaDTO actualizarMarca(Long id, MarcaDTO marcaDTO){
        Marca marca = mapper.map(marcaDTO, Marca.class);
        marca.setId(id);
        marcaRepository.save(marca);

        MarcaDTO marcaRespuesta = mapper.map(marca, MarcaDTO.class);

        return marcaRespuesta;
    }

    public MarcaDTO borrarMarca(Long id){
        Marca marca = marcaRepository.findById(id).get();
        marcaRepository.delete(marca);

        MarcaDTO marcaRespuesta = mapper.map(marca, MarcaDTO.class);

        return marcaRespuesta;
    }



}
