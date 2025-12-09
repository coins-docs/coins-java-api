package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetQuoteRequest {

    @NotBlank(message = "sourceCurrency cannot be blank")
    private String sourceCurrency;
    
    @NotBlank(message = "targetCurrency cannot be blank")
    private String targetCurrency;
    private String sourceAmount;
    private String targetAmount;
    private BizType type;
}
