package com.assignment.andevis;

import com.assignment.andevis.jsonobject.JsonCurrencyResponse;
import com.assignment.andevis.model.Currency;
import com.assignment.andevis.model.CurrencyRate;
import com.assignment.andevis.repository.CurrencyRateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@SpringBootApplication
public class CurrencyCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCalculatorApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CurrencyRateRepository currencyRateRepo) {
        return args -> {
            String rawJson = new RestTemplate().getForObject("http://api.exchangeratesapi.io/v1/latest?access_key=666c7f1b11f090a2f5e8e466bdcdd78e", String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonCurrencyResponse jsonResponse = mapper.readValue(rawJson, JsonCurrencyResponse.class);

            for (Map.Entry<String, BigDecimal> entries : jsonResponse.getRates().entrySet()) {

                currencyRateRepo.save(new CurrencyRate(new Currency(entries.getKey(), entries.getValue()), jsonResponse.getBase(), jsonResponse.getDate()));
            }
        };
    }

}
