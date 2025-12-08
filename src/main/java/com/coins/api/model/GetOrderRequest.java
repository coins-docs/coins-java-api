package com.coins.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderRequest {
    
    @Min(1)
    private Long orderId;
    
    private String origClientOrderId;
}
