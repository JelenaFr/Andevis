package com.assignment.andevis.service;

import com.assignment.andevis.config.LoadDatabase;
import com.assignment.andevis.dto.JsonCurrencyResponse;
import com.assignment.andevis.model.Currency;
import com.assignment.andevis.model.CurrencyRate;
import com.assignment.andevis.model.History;
import com.assignment.andevis.model.User;
import com.assignment.andevis.repository.CurrencyRateRepository;
import com.assignment.andevis.repository.HistoryRepository;
import com.assignment.andevis.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculatorService {
    @Autowired
    CurrencyRateRepository currencyRateRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    UserRepository userRepository;


    public List<String> findAllCodes() {
        List<String> list = currencyRateRepository.getListCurrencyCode();
        List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
        return sortedList;


    }

    public Double convertCurrency(Double amount, String fromCurrency, String toCurrency, Principal principal)  {

        DecimalFormat df = new DecimalFormat("#.###");
        Double covertSum = (Double.valueOf(df.format(amount * currencyRateRepository.getactualRate(fromCurrency, toCurrency))));

        if (amount != null && fromCurrency != null && toCurrency != null) {

            String currentPrincipalName = principal.getName();
            User user = userRepository.findUserByEmail(currentPrincipalName);
            History newHistory = new History();
            newHistory.setAmount(amount);
            newHistory.setResult(covertSum);
            newHistory.setCurrencyFrom(fromCurrency);
            newHistory.setCurrencyTo(toCurrency);
            newHistory.setUserId(user.getId());
            newHistory.setOperationTime(LocalDateTime.now());
            historyRepository.save(newHistory);
        }
        return covertSum;
    }



    public void updateDatabase () throws JsonProcessingException {
        System.out.println(currencyRateRepository.findCurrentDay().toLocalDateTime());
        System.out.println(LocalDateTime.now());
        boolean isAfter = LocalDateTime.now().isAfter(currencyRateRepository.findCurrentDay().toLocalDateTime());
        System.out.println(isAfter);

        foo(currencyRateRepository);

        }

    public static void foo(CurrencyRateRepository currencyRateRepository) throws JsonProcessingException {
        String rawJson = new RestTemplate().getForObject("http://api.exchangeratesapi.io/v1/latest?access_key=666c7f1b11f090a2f5e8e466bdcdd78e", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonCurrencyResponse jsonResponse = mapper.readValue(rawJson, JsonCurrencyResponse.class);

        for (Map.Entry<String, BigDecimal> entries : jsonResponse.getRates().entrySet()) {

            currencyRateRepository.save(new CurrencyRate(new Currency(entries.getKey(), entries.getValue()), jsonResponse.getBase(), jsonResponse.getDate()));
        }
    }


}

