package com.giralsoft.pruebaBackendFacturas.infraestructure.controller;

import com.giralsoft.pruebaBackendFacturas.application.services.FacturaService;
import com.giralsoft.pruebaBackendFacturas.infraestructure.entity.FacturaEntity;
import com.giralsoft.pruebaBackendFacturas.infraestructure.mapper.FacturaMapper;
import com.giralsoft.pruebaBackendFacturas.infraestructure.utils.CsvHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/csv")
public class FacturaController {
    private final FacturaService facturaService;
    private final FacturaMapper facturaMapper;

    public FacturaController(FacturaService facturaService, FacturaMapper facturaMapper) {
        this.facturaService = facturaService;
        this.facturaMapper = facturaMapper;
    }

    @GetMapping
    public ResponseEntity<Iterable<FacturaEntity>> getAllFacturas() {
        try {
            Iterable<FacturaEntity> facturas = facturaMapper.toFacturasEntity(facturaService.getAllFacturas());

            if (!facturas.iterator().hasNext()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(facturas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CsvHelper.hasCSVFormat(file)) {
            try {
                facturaService.saveFile(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("/async/{id}")
    public SseEmitter async(@PathVariable("id") Integer id) {
        return facturaService.async(id);
    }

}
