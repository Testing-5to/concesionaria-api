package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Impuesto;
import com.autos.concesionaria.repository.ImpuestoRepository;
import com.autos.concesionaria.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpuestoService {

    // Repositorio de Impuesto
    @Autowired
    private final ImpuestoRepository impuestoRepository;

    // Repositorio de Pais
    @Autowired
    private final PaisRepository paisRepository;

    // ArrayList de los países de América
    private final String[] paisesAmerica = {"Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador", "Guatemala", "Honduras", "México", "Nicaragua", "Panamá", "Paraguay", "Perú", "República Dominicana", "Uruguay", "Venezuela"};

    /**
     * Obtener todos los impuestos
     *
     * @return List<Impuesto> Lista de impuestos
     */
    public List<Impuesto> buscarImpuestos() {
        return impuestoRepository.findAll();
    }

    /**
     * Obtener un impuesto por su ID
     *
     * @param id El ID del impuesto
     * @return Impuesto El impuesto
     */
    public Impuesto getImpuesto(Long id) {
        return impuestoRepository.findById(id).orElse(null);
    }

    /**
     * En base a un país y un precio de venta, devuelve el impuesto correspondiente
     *
     * @param pais El país
     * @param precioVenta El precio de venta
     * @return Impuesto El impuesto
     */
    public Impuesto buscarImpuestoPorPaisYPrecioVenta(String pais, Double precioVenta) {
        if (pais.equals("Argentina")) {
            // Si el país es Argentina, aplica impuesto Nacional
            // Obtenemos los rangos de impuestos Nacionales
            List<Impuesto> impuestosNacionales = impuestoRepository.findAllByRegion("Nacional");
            // Recorremos los impuestos Nacionales
            for (Impuesto impuesto : impuestosNacionales)
                // Si el precio de venta está dentro del rango, devolvemos el impuesto
                if (precioVenta >= impuesto.getRangoMinimo() && precioVenta <= impuesto.getRangoMaximo())
                    return impuesto;
            // Si no se encuentra el impuesto, devolvemos null
            return null;
        } else if (estaEnAmerica(pais)) {
            // Si el país es de América, aplica impuesto de América
            return impuestoRepository.findByRegion("America");
        } else {
            // Si el país no es de América, aplica impuesto Internacional
            // Buscamos si existe el país en la base de datos
            if (paisRepository.existsByNombre(pais))
                // Si existe, devolvemos el impuesto Internacional
                return impuestoRepository.findByRegion("Mundo");
            else
                // Si no existe, devolvemos null
                return null;
        }
    }

    /**
     * Verifica si un país está en América
     *
     * @param pais El país
     * @return boolean Si está en América
     */
    private boolean estaEnAmerica(String pais) {
        // Recorremos el array de países de América
        for (String paisAmerica : paisesAmerica)
            // Si el país está en el array, devolvemos true
            if (paisAmerica.equals(pais))
                return true;
        // Si no está en el array, devolvemos false
        return false;
    }

}
