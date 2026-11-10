package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatCashOutResponse {

    /**
     * 我们本地订单自增id
     */
    private String externalOrderId;

    /**
     * 商户传入的订单号码（对应我们系统order_id）
     */
    private String internalOrderId;

}
