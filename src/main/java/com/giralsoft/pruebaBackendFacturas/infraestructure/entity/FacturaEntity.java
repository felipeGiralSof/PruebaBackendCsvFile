package com.giralsoft.pruebaBackendFacturas.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoFactura;

    private String nombre;
    private String apellido;
    private String dirección;
    private BigDecimal valorApagar;
    private Date fechavencimiento;
    private Date fechaOportunaPago;
    private String estado;
}
