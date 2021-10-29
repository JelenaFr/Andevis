package com.assignment.andevis.repository;



import com.assignment.andevis.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
@Query(value = "select distinct code from currency_rate", nativeQuery = true)
    List<String> getListCurrencyCode();


    @Query(value = "SELECT  rate/(SELECT  rate  from currency_rate where code like :fromCurrency)  from currency_rate where code like :toCurrency ;", nativeQuery = true)
    Double getactualRate(String fromCurrency, String toCurrency);
}
