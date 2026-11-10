package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedTradingPair {
    
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal minSourceAmount;
    private BigDecimal maxSourceAmount;
    private Integer precision;
}
