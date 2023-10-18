package com.giralsoft.pruebaBackendFacturas.infraestructure.adapter;

import com.giralsoft.pruebaBackendFacturas.application.repository.FacturaRepository;
import com.giralsoft.pruebaBackendFacturas.domain.Factura;
import com.giralsoft.pruebaBackendFacturas.infraestructure.entity.FacturaEntity;
import com.giralsoft.pruebaBackendFacturas.infraestructure.mapper.FacturaMapper;
import com.giralsoft.pruebaBackendFacturas.infraestructure.utils.CsvHelper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class FacturaRepositoryImpl implements FacturaRepository {

    private final FacturaCrudRepository facturaCrudRepository;
    private final FacturaMapper facturaMapper;

    public FacturaRepositoryImpl(FacturaCrudRepository facturaCrudRepository, FacturaMapper facturaMapper) {
        this.facturaCrudRepository = facturaCrudRepository;
        this.facturaMapper = facturaMapper;
    }
    @Override
    public void saveFile(MultipartFile file) {
        try {
            List<FacturaEntity> csvModels = CsvHelper.csvToTutorials(file.getInputStream());
            facturaCrudRepository.saveAll(csvModels);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    @Override
    public Iterable<Factura> getAllFacturas() {
        return facturaMapper.toFacturas(facturaCrudRepository.findAll());
    }

    @Override
    public SseEmitter async(Integer id) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Factura dataSet = updateReturnData(id);
            try {
                randomDelay();
                emitter.send(dataSet);
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }

    private Factura updateReturnData (Integer id) {
        Optional<FacturaEntity> factura = facturaCrudRepository.findById(id);
        if(factura.isPresent()) {
            Factura facturita = facturaMapper.toFactura(factura.get());
            facturita.setEstado("Rechazado");
            facturaCrudRepository.save(facturaMapper.toFacturaEntity(facturita));
            return facturita;
        }
        return null;
    }

    private void randomDelay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
