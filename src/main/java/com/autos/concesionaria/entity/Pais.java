package com.autos.concesionaria.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pais")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID del pais
    private Long id;

    // El Nombre del pais
    private String nombre;

    // La abreviatura del pais, dos letras que lo identifican
    private String abreviatura;

}
