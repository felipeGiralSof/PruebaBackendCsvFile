package com.giralsoft.pruebaBackendFacturas.infraestructure.utils;

import com.giralsoft.pruebaBackendFacturas.infraestructure.entity.FacturaEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Codigo", "Nombre", "Apellido", "Direccion", "ValorApagar", "FechaVencimiento", "FechaOportunaPago", "Estado" };

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<FacturaEntity> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<FacturaEntity> facturas = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                FacturaEntity factura = new FacturaEntity(
                        Integer.parseInt(csvRecord.get("Codigo")),
                        csvRecord.get("Nombre"),
                        csvRecord.get("Apellido"),
                        csvRecord.get("Direccion"),
                        new BigDecimal(csvRecord.get("ValorApagar")),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("FechaVencimiento")),
                        new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("FechaOportunaPago")),
                        csvRecord.get("Estado")
                );

                facturas.add(factura);
            }

            return facturas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse CSV file Data: " + e.getMessage());
        }
    }
}
