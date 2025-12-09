package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Request object for querying historical trades
 */
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryTradeRequest {

    @NotBlank(message = "symbol cannot be blank")
    private String symbol;
    
    @Min(value = 1, message = "orderId must be greater than 0")
    private Long orderId;
    
    @Min(value = 1, message = "startTime must be greater than 0")
    private Long startTime;
    
    @Min(value = 1, message = "endTime must be greater than 0")
    private Long endTime;
    
    @Min(value = 1, message = "fromId must be greater than 0")
    private Long fromId;
    
    @Min(value = 1, message = "limit must be greater than 0")
    @Max(value = 1000, message = "limit must be less than or equal to 1000")
    private Integer limit = 500;

    public HistoryTradeRequest() {}

    public HistoryTradeRequest(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
