package com.autos.concesionaria.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "empleado")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // El ID del empleado
    private Long id;

    // El nombre del empleado
    private String nombre;

    // El apellido del empleado
    private String apellido;

    // El teléfono del empleado
    private String telefono;

    // El DNI del empleado
    private int dni;

    // El email del empleado
    private String email;

    // La fecha de ingreso del empleado
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaIngreso;

    // La fecha de egreso del empleado, puede ser nula
    @Null
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaEgreso;

    // El salario del empleado
    private double salario;

    // El rol del empleado
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    // La dirección del empleado
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

}
