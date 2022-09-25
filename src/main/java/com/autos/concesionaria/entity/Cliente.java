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
    private Long id;
    
    private String nombre;

    private String apellido;

    private String telefono;

    private Integer documento;

    private String email;

    @Column(name = "es_cliente")
    private Boolean esCliente;

    //relationship with direccion
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

}
