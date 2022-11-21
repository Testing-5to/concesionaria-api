package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService {

    // Repositorio de Modelo
    @Autowired
    private ModeloRepository modeloRepository;

    /**
     * Método que devuelve todos los modelos
     *
     * @return List<Modelo> Lista de modelos
     */
    public List<Modelo> getModelos() {
        return modeloRepository.findAll();
    }

    /**
     * Método que devuelve los modelos de una marca
     *
     * @param String marca nombre de la marca
     * @return List<Modelo> Lista de modelos de la marca
     */
    public List<Modelo> getModelosByMarca(String marca) {
        return modeloRepository.findAllByMarca_Nombre(marca);
    }

    /**
     * Método que devuelve los modelos de un tipo de vehículo
     *
     * @param String tipoVehiculo nombre del tipo de vehículo
     * @return List<Modelo> Lista de modelos del tipo de vehículo
     */
    public List<Modelo> getModelosByTipoVehiculo(String tipoVehiculo) {
        return modeloRepository.findAllByTipoVehiculo_Nombre(tipoVehiculo);
    }

    /**
     * Método que devuelve los modelos de una marca y un tipo de vehículo
     *
     * @param String marca nombre de la marca
     * @param String tipoVehiculo nombre del tipo de vehículo
     * @return List<Modelo> Lista de modelos de la marca y del tipo de vehículo
     */
    public List<Modelo> getModelosByMarcaAndTipoVehiculo(String marca, String tipoVehiculo) {
        return modeloRepository.findAllByMarca_NombreAndTipoVehiculo_Nombre(marca, tipoVehiculo);
    }

    /**
     * Método que devuelve un modelo por su id
     *
     * @param Long id del modelo
     * @return Modelo encontrado
     */
    public Modelo getModelo(Long id) {
        return modeloRepository.findById(id).orElse(null);
    }

    /**
     * Método que crea un modelo
     *
     * @param Modelo modelo a crear
     * @return Modelo creado
     */
    public Modelo crearModelo(Modelo modelo) {
        Modelo nuevoModelo = modeloRepository.save(modelo);
        return nuevoModelo;
    }

    /**
     * Método que actualiza un modelo
     *
     * @param Long   id del modelo a actualizar
     * @param Modelo modelo a actualizar
     * @return Modelo actualizado
     */
    public Modelo actualizarModelo(Long id, Modelo modelo) {
        Modelo modeloActual = modeloRepository.findById(id).orElse(null);
        modeloActual.setNombre(modelo.getNombre());
        modeloActual.setMarca(modelo.getMarca());
        modeloActual.setTipoVehiculo(modelo.getTipoVehiculo());
        return modeloRepository.save(modeloActual);
    }

    /**
     * Método que elimina un modelo
     *
     * @param Long id del modelo a eliminar
     * @return void
     */
    public void borrarModelo(Long id) {
        modeloRepository.deleteById(id);
    }

    /**
     * Método que cuenta la cantidad de modelos para un tipo de vehículo
     *
     * @param Long id del tipo de vehículo
     * @return int cantidad de modelos para el tipo de vehículo
     */
    public int contarModelosPorTipoVehiculo(Long id) {
        return modeloRepository.countByTipoVehiculo_Id(id);
    }

    /**
     * Método que cuenta la cantidad de modelos para una marca
     *
     * @param Long id de la marca
     * @return int cantidad de modelos para la marca
     */
    public int contarModelosPorMarca(Long id) {
        return modeloRepository.countByMarca_Id(id);
    }

}
