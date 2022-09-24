package com.autos.concesionaria.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "direccion")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID de la direccion
    private Long id;

    // La calle
    private String calle;

    // El numero de la direccion
    @Min(value = 0, message = "El numero de la direccion debe ser mayor a 0")
    private int numero;

    // El piso de la direccion
    @Nullable
    private String piso;

    // El departamento de la direccion
    @Nullable
    private String departamento;

    // La localidad de la direccion
    @ManyToOne
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

}
