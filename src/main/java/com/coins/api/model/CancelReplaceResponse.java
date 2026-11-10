package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelReplaceResponse {
    private String cancelResult;
    private String newOrderResult;
    private String cancelResponse;
    private String newOrderResponse;
    
    // Cancel order details
    private CancelOrderInfo cancelOrderInfo;
    
    // New order details  
    private NewOrderInfo newOrderInfo;
    @JsonUnwrapped
    private ErrorRet errorRet;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CancelOrderInfo {
        private String symbol;
        private Long orderId;
        private String clientOrderId;
        private Long transactTime;
        private BigDecimal price;
        private BigDecimal origQty;
        private BigDecimal executedQty;
        private BigDecimal origQuoteOrderQty;
        private BigDecimal cummulativeQuoteQty;
        private String status;
        private String timeInForce;
        private String type;
        private String side;
        private String stpFlag;
        private BigDecimal stopPrice;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NewOrderInfo {
        private String symbol;
        private Long orderId;
        private String clientOrderId;
        private Long transactTime;
        private BigDecimal price;
        private BigDecimal origQty;
        private BigDecimal executedQty;
        private BigDecimal cummulativeQuoteQty;
        private String status;
        private String timeInForce;
        private String type;
        private String side;
        private BigDecimal stopPrice;
    }
}
