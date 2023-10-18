package com.giralsoft.pruebaBackendFacturas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PruebaBackendFacturasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaBackendFacturasApplication.class, args);
	}

}
