package com.coins.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderRequest {

    @NotBlank
    @Length(max = 20)
    private String symbol;
    
    @NotBlank
    private String side;
    
    @NotBlank
    private String type;
    
    private String timeInForce;
    
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal quantity;
    
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal quoteOrderQty;
    
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal price;
    
    private String newClientOrderId;
    
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal stopPrice;
    
    private String newOrderRespType;
    private String stpFlag;
}
