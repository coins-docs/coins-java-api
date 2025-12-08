package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
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
public class QueryFiatOrderRequest {
    
    /**
     * 订单id
     */
    private String internalOrderId;
    
    /**
     * 订单id
     */
    private String externalOrderId;
    
    /**
     * 交易类型 1:deposit 2:withdraw 3:buy 4:sell
     */
    private String transactionType;
    
    /**
     * 法币币种
     */
    private String fiatCurrency;
    
    /**
     * 数字货币币种
     */
    private String cryptoCurrency;
    
    /**
     * 渠道
     */
    private String transactionChannel;
    
    /**
     * 次级渠道
     */
    private String transactionSubject;
    
    /**
     * 订单状态
     */
    private String status;
    
    /**
     * 订单开始时间
     */
    private Date startDate;
    
    /**
     * 订单结束时间
     */
    private Date endDate;
    
    /**
     * 订单开始时间
     */
    private Long startTime;
    
    /**
     * 订单结束时间
     */
    private Long endTime;
    
    // Pagination fields
    @Min(value = 1)
    @Builder.Default
    private Integer page = 1;
    
    @Min(value = 1)
    @Builder.Default
    private Integer size = 20;
}
