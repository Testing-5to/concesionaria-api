package com.autos.concesionaria.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "marca")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String pais;

}
