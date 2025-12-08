package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class GetOrderHistoryRequest {
    
    @Min(value = 1)
    private Long startTime;
    
    @Min(value = 1)
    private Long endTime;
    
    @Min(value = 1)
    @Builder.Default
    private Integer page = 1;
    
    @Min(value = 1)
    @Builder.Default
    private Integer size = 20;
}
