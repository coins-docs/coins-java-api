package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryOrdersRequest {
    
    private String symbol;
    
    @Min(value = 1, message = "orderId must be greater than 0")
    private Long orderId;
    
    @Min(value = 1, message = "startTime must be greater than 0")
    private Long startTime;
    
    @Min(value = 1, message = "endTime must be greater than 0")
    private Long endTime;
    
    @Min(value = 1, message = "limit must be greater than 0")
    @Max(value = 1000, message = "limit must be less than or equal to 1000")
    private Integer limit;
}
