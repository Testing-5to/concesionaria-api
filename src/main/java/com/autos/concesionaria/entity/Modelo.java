package com.autos.concesionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "modelo")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID del modelo
    private Long id;

    // El nombre del modelo
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    // La marca del modelo
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    // El tipo de vehiculo del modelo
    private TipoVehiculo tipoVehiculo;

}
