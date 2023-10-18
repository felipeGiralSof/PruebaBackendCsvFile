package com.giralsoft.pruebaBackendFacturas.infraestructure.adapter;

import com.giralsoft.pruebaBackendFacturas.infraestructure.entity.FacturaEntity;
import org.springframework.data.repository.CrudRepository;

public interface FacturaCrudRepository extends CrudRepository<FacturaEntity, Integer> {
}
