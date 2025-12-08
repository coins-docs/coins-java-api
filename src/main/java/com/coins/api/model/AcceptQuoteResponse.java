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
public class AcceptQuoteResponse {
    
    private String orderId;
    private String quoteId;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
    private BigDecimal price;
    private String status;
    private Long createTime;
}
