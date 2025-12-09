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
    
    @Min(value = 1, message = "startTime must be greater than 0")
    private Long startTime;
    
    @Min(value = 1, message = "endTime must be greater than 0")
    private Long endTime;
    
    @Min(value = 1, message = "page must be greater than 0")
    @Builder.Default
    private Integer page = 1;
    
    @Min(value = 1, message = "size must be greater than 0")
    @Builder.Default
    private Integer size = 20;
}
