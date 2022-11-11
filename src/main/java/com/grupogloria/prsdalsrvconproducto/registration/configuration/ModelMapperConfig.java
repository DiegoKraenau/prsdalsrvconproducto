package com.grupogloria.prsdalsrvconproducto.registration.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    ModelMapper mm = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        mm.getConfiguration().setAmbiguityIgnored(true);
        return mm;
    }
}
