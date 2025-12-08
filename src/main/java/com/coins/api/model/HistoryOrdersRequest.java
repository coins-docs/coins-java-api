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
    
    @Min(1)
    private Long orderId;
    
    @Min(1)
    private Long startTime;
    
    @Min(1)
    private Long endTime;
    
    @Min(1)
    @Max(1000)
    private Integer limit;
}
