package com.autos.concesionaria.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

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
    @ManyToOne
    @JoinColumn(name = "calle_id")
    private Calle calle;

    // El numero de la direccion
    @Min(value = 0, message = "El numero de la direccion debe ser mayor a 0")
    private int numero;

    // El piso de la direccion
    private String piso;

    // El departamento de la direccion
    private String departamento;

}
