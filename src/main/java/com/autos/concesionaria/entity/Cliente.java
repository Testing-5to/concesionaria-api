package com.autos.concesionaria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id of the cliente
    private Long id;

    // Nombre of the cliente
    private String nombre;

    // Apellido of the cliente
    private String apellido;

    // Teléfono of the cliente
    private String telefono;

    // DNI of the cliente
    private Integer dni;

    // Email of the cliente
    private String email;

    // Fecha de nacimiento of the cliente
    @Column(name = "es_cliente")
    private Boolean esCliente;

    // dirección of the cliente
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

}
