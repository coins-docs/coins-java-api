package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOrderResponse {
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
    private List<FillInfo> fills;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FillInfo {
        private BigDecimal price;
        private BigDecimal qty;
        private BigDecimal commission;
        private String commissionAsset;
        private Long tradeId;
    }
}
