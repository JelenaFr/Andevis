package com.assignment.andevis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Currency {

    private String code;
    private BigDecimal rate;
    @PrePersist
    @PreUpdate
    public void codeToUpper() {
        code = code.toUpperCase();
    }
}
