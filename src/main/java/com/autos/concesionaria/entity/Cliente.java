package com.autos.concesionaria.entity;

import lombok.*;

import javax.persistence.*;

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
    // Id del cliente
    private Long id;

    // Nombre del cliente
    private String nombre;

    // Apellido del cliente
    private String apellido;

    // Teléfono del cliente
    private String telefono;

    // DNI del cliente
    @Column(unique = true)
    private Integer dni;

    // Email del cliente
    private String email;

    // Si el cliente es cliente
    @Column(name = "es_cliente")
    private Boolean esCliente;

    // Dirección del cliente
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

}
