package com.assignment.andevis.jsonobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JsonCurrencyResponse {
    Map<String, BigDecimal> rates;
    String base;
    Date date;
}
