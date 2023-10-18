package com.giralsoft.pruebaBackendFacturas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    private Integer codigoFactura;
    private String nombre;
    private String apellido;
    private String dirección;
    private BigDecimal valorApagar;
    private Date fechavencimiento;
    private Date fechaOportunaPago;
    private String estado;
}
