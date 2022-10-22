package com.autos.concesionaria.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "impuesto")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Impuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id del impuesto
    private Long id;

    // Región del impuesto
    private String region;

    // Porcentaje del impuesto
    private Double porcentaje;

    // Rango mínimo del impuesto
    private Double rangoMinimo;

    // Rango máximo del impuesto
    private Double rangoMaximo;

}
