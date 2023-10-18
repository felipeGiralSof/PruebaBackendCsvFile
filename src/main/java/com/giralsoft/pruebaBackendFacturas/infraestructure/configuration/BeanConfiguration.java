package com.giralsoft.pruebaBackendFacturas.infraestructure.configuration;

import com.giralsoft.pruebaBackendFacturas.application.repository.FacturaRepository;
import com.giralsoft.pruebaBackendFacturas.application.services.FacturaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class BeanConfiguration {
    @Bean
    public FacturaService facturaService(FacturaRepository facturaRepository){
        return new FacturaService(facturaRepository);
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setThreadNamePrefix("mvc-task-");
        return threadPoolTaskExecutor;
    }
}
