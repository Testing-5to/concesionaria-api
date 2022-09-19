package com.autos.concesionaria.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "calle")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Calle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID de la calle
    private Long id;

    // El nombre de la calle
    private String nombre;

    // La localidad a la que pertenece la calle
    @ManyToOne
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;

}
