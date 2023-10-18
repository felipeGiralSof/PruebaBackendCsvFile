package com.giralsoft.pruebaBackendFacturas.application.repository;

import com.giralsoft.pruebaBackendFacturas.domain.Factura;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface FacturaRepository {

    void saveFile(MultipartFile file);
    Iterable<Factura> getAllFacturas();
    SseEmitter async(Integer id);
}
