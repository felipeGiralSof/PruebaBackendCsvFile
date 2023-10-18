package com.giralsoft.pruebaBackendFacturas.application.services;

import com.giralsoft.pruebaBackendFacturas.application.repository.FacturaRepository;
import com.giralsoft.pruebaBackendFacturas.domain.Factura;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }
    public Iterable<Factura> getAllFacturas(){
        return facturaRepository.getAllFacturas();
    }
    public void saveFile(MultipartFile file){
        facturaRepository.saveFile(file);
    }

    public SseEmitter async (Integer id){
        return facturaRepository.async(id);
    }



}
