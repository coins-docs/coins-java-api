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

    @NotBlank(message = "symbol cannot be blank")
    @Length(max = 20, message = "symbol length must not exceed 20 characters")
    private String symbol;
    
    @NotBlank(message = "side cannot be blank")
    private String side;
    
    @NotBlank(message = "type cannot be blank")
    private String type;
    
    private String timeInForce;
    
    @DecimalMin(value = "0", inclusive = false, message = "quantity must be greater than 0")
    private BigDecimal quantity;
    
    @DecimalMin(value = "0", inclusive = false, message = "quoteOrderQty must be greater than 0")
    private BigDecimal quoteOrderQty;
    
    @DecimalMin(value = "0", inclusive = false, message = "price must be greater than 0")
    private BigDecimal price;
    
    private String newClientOrderId;
    
    @DecimalMin(value = "0", inclusive = false, message = "stopPrice must be greater than 0")
    private BigDecimal stopPrice;
    
    private String newOrderRespType;
    private String stpFlag;
}
