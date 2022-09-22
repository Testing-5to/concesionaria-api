package com.autos.concesionaria.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_vehiculo")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID del tipo de vehiculo
    private Long id;

    // El nombre del tipo de vehiculo
    private String nombre;

}