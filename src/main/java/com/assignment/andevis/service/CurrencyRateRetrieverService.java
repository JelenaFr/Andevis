//package com.assignment.andevis.service;
//
//import com.assignment.andevis.config.LoadDatabase;
//import com.assignment.andevis.repository.CurrencyRateRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//
//public class CurrencyRateRetrieverService {
//
//    @Autowired
//    CurrencyRateRepository currencyRateRepository;
//
//
//    public void checkDatabaseActuality() {
//        if (
//            currencyRateRepository.findCurrentDay() == null) {
//            LoadDatabase loadDatabase = new LoadDatabase();
//
//        }
//        ;
//    }
//}
