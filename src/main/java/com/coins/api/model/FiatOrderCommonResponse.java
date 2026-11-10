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
public class FiatOrderCommonResponse {
    
    /**
     * 我们本地订单自增id
     */
    private String externalOrderId;
    
    /**
     * 商户传入的订单号码（对应我们系统order_id）
     */
    private String internalOrderId;
    
    private String paymentOrderId;
    
    /**
     * 法币类型
     */
    private String fiatCurrency;
    
    /**
     * 法币数量
     */
    private BigDecimal fiatAmount;
    
    /**
     * 1:deposit -1:withdraw 2:buy -2:sell
     */
    private Integer transactionType;
    
    /**
     * 渠道
     */
    private String transactionChannel;
    
    /**
     * 次级渠道
     */
    private String transactionSubject;
    
    /**
     * 渠道名
     */
    private String transactionChannelName;
    
    /**
     * 次级渠道名字
     */
    private String transactionSubjectName;
    
    /**
     * 次级渠道
     */
    private String transactionSubjectType;
    
    /**
     * 手续费币种
     */
    private String feeCurrency;
    
    /**
     * 渠道手续费
     */
    private BigDecimal channelFee;
    
    /**
     * 平台手续费
     */
    private BigDecimal platformFee;
    
    /**
     * 订单状态
     */
    private String status;
    
    /**
     * 异常code
     */
    private String errorCode;
    
    /**
     * 异常msg
     */
    private String errorMessage;
    
    /**
     * 订单完成时间
     */
    private Date completedTime;
    
    /**
     * 订单来源, web、ios、openapi
     */
    private String source;
    
    /**
     * 创建时间
     */
    private Date createdAt;
    
    /**
     * 扩展Map
     */
    private Map<String, Object> orderExtendedMap;
    
    private Boolean dealCancel;
}
