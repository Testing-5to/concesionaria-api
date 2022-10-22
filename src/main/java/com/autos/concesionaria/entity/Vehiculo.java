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
    // Id del vehiculo
    private Long id;

    // Año del vehiculo
    private Integer anio;

    // Cantidad en stock del vehiculo
    private Integer cantidad;

    // Si el vehiculo es importado o no
    private Boolean importado;

    // Path de la imagen del vehiculo
    private String imagen;

    // Precio de venta del vehiculo
    @Column(name = "precio_venta")
    private Double precioVenta;

    // Precio de compra del vehiculo
    @Column(name = "precio_compra")
    private Double precioCompra;

    // Modelo del vehiculo
    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;
    
}
