package com.autos.concesionaria.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "marca")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID de la marca
    private Long id;

    // El nombre de la marca
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    // El pais de origen de la marca
    private Pais pais;

}
