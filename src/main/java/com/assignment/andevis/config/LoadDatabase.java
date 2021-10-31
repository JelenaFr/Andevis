package com.assignment.andevis.config;

import com.assignment.andevis.dto.JsonCurrencyResponse;
import com.assignment.andevis.model.Currency;
import com.assignment.andevis.model.CurrencyRate;
import com.assignment.andevis.repository.CurrencyRateRepository;
import com.assignment.andevis.service.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(CurrencyRateRepository currencyRateRepository) {

        return args -> {

            CalculatorService.foo(currencyRateRepository);
        };


    }
}
