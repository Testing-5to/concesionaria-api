package com.autos.concesionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pais")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID del pais
    private Long id;

    // El nombre del pais
    private String nombre;

    @Column(length = 2)
    // La abreviatura del pais, dos letras que lo identifican
    private String abreviatura;

}
