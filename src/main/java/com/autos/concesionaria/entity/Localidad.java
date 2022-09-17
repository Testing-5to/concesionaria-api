package com.autos.concesionaria.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "localidad")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID de la localidad
    private Long id;

    // El nombre de la localidad
    private String nombre;

    // El codigo postal de la localidad
    @Min(value = 0, message = "El valor debe ser positivo")
    private int codigoPostal;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    // La provincia a la que pertenece la localidad
    private Provincia provincia;

}
