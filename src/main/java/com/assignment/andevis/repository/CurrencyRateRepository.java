package com.assignment.andevis.repository;


import com.assignment.andevis.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query(value = "SELECT MAX(date) FROM currency_rate;", nativeQuery = true)
    Timestamp findLastAvailableDay();


    @Query(value = "SELECT DISTINCT code FROM currency_rate", nativeQuery = true)
    List<String> getListCurrencyCode();


    @Query(value = "SELECT  rate/(SELECT rate FROM currency_rate WHERE code LIKE :fromCurrency ORDER BY date DESC LIMIT 1)  FROM currency_rate WHERE code LIKE :toCurrency ORDER BY date DESC LIMIT 1 ;", nativeQuery = true)
    Double getRate(String fromCurrency, String toCurrency);
}
