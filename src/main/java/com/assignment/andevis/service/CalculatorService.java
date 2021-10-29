package com.assignment.andevis.service;

import com.assignment.andevis.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculatorService {
    @Autowired
    CurrencyRateRepository currencyRateRepository;


    public List<String> findAllCodes() {
        List<String> list = currencyRateRepository.getListCurrencyCode();
        List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
        return sortedList;


    }

    public Double convertCurrency(Double amount, String fromCurrency, String toCurrency) {
        Double covertSum = amount * currencyRateRepository.getactualRate(fromCurrency, toCurrency);
        System.out.println(covertSum + "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" );
        return covertSum;
    }
}
