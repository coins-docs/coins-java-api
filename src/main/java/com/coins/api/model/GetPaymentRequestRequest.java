package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPaymentRequestRequest {
    
    private String id;
    
    @Min(value = 1, message = "startTime must be greater than 0")
    private Long startTime;
    
    @Min(value = 1, message = "endTime must be greater than 0")
    private Long endTime;
    
    @Min(value = 1, message = "limit must be greater than 0")
    @Max(value = 1000, message = "limit must be less than or equal to 1000")
    @Builder.Default
    private Integer limit = 500;
}
