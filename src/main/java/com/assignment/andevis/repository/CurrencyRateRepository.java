package com.assignment.andevis.repository;


import com.assignment.andevis.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query(value = "select MAX(date) from currency_rate;" , nativeQuery = true)
    Timestamp findCurrentDay();

    @Query(value = "select distinct code from currency_rate", nativeQuery = true)
    List<String> getListCurrencyCode();


    @Query(value = "SELECT  rate/(select * from currency_rate where code like :fromCurrency order by date desc limit 1)  from currency_rate where code like :toCurrency order by date desc limit 1 ;", nativeQuery = true)
    Double getactualRate(String fromCurrency, String toCurrency);
}
