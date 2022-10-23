package com.autos.concesionaria.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    // Id de la venta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de la venta
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Null
    private LocalDateTime fecha;

    // Cantidad de vehiculos vendidos
    @Min(1)
    private int cantidadVehiculos;

    // Precio unitario del vehiculo
    private double precioUnitario;

    // Impuesto de la venta
    @ManyToOne
    @JoinColumn(name = "impuesto_id")
    private Impuesto impuesto;

    // Impuesto en pesos de la venta
    private double impuestoPesos;

    // Porcentaje de impuesto de la venta
    private double impuestoPorcentaje;

    // Vehiculo de la venta
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    // Vendedor de la venta
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado vendedor;

    // Cliente de la venta
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /**
     * Calcular el subtotal de la venta
     *
     * @return El subtotal de la venta
     */
    public double calcularSubtotal() {
        return this.precioUnitario * this.cantidadVehiculos;
    }

    /**
     * Calcular los impuestos de la venta
     *
     * @return Los impuestos de la venta
     */
    public double calcularImpuestos() {
        return this.calcularSubtotal() * this.impuestoPorcentaje;
    }

    /**
     * Calcula el precio total de la venta
     *
     * @return El precio total de la venta
     */
    public double calcularTotal() {
        return this.calcularSubtotal() + this.calcularImpuestos();
    }

}
