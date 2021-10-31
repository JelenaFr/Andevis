package com.assignment.andevis.config;

import com.assignment.andevis.repository.CurrencyRateRepository;
import com.assignment.andevis.service.RateConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(CurrencyRateRepository currencyRateRepository) {
        return args -> {

            RateConverterService.updateDatabase(currencyRateRepository);
        };


    }
}
