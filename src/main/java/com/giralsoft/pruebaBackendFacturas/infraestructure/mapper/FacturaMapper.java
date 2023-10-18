package com.giralsoft.pruebaBackendFacturas.infraestructure.mapper;

import com.giralsoft.pruebaBackendFacturas.domain.Factura;
import com.giralsoft.pruebaBackendFacturas.infraestructure.entity.FacturaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    @Mappings(
            {
                    @Mapping(source = "codigoFactura", target = "codigoFactura"),
                    @Mapping(source = "nombre", target = "nombre"),
                    @Mapping(source = "apellido", target = "apellido"),
                    @Mapping(source = "dirección", target = "dirección"),
                    @Mapping(source = "valorApagar", target = "valorApagar"),
                    @Mapping(source = "fechavencimiento", target = "fechavencimiento"),
                    @Mapping(source = "fechaOportunaPago", target = "fechaOportunaPago"),
                    @Mapping(source = "estado", target = "estado"),
            }
    )
    Factura toFactura(FacturaEntity facturaEntity);
    Iterable<Factura> toFacturas(Iterable<FacturaEntity> facturaEntities);

    @InheritInverseConfiguration
    FacturaEntity toFacturaEntity(Factura factura);
    Iterable<FacturaEntity> toFacturasEntity(Iterable<Factura> facturaEntities);
}
