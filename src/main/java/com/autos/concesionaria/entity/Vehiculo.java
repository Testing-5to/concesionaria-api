package com.autos.concesionaria.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehiculo")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id of the vehiculo
    private Long id;

    // VIN of the vehiculo
    private String nombre;

    // Anio of the vehiculo
    private Integer anio;

    // Kilometraje of the vehiculo
    private Integer kilometros;

    // If the vehiculo is imported or not
    private Boolean importado;

    // Path to the image of the vehiculo
    private String imagen;

    // Precio de venta of the vehiculo
    @Column(name = "precio_venta")
    private Double precioVenta;

    // Precio de compra of the vehiculo
    @Column(name = "precio_compra")
    private Double precioCompra;

    // Modelo of the vehiculo
    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;
    
}
