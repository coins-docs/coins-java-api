package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatChannelConfigResponse {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 渠道
     */
    private String transactionChannel;
    
    /**
     * 渠道描述
     */
    private String transactionChannelName;
    
    /**
     * 次级渠道
     */
    private String transactionSubject;
    
    /**
     * 次级渠道类型
     */
    private String transactionSubjectType;
    
    /**
     * 渠道类型 label
     */
    private String transactionSubjectTypeLabel;
    
    /**
     * 次级渠道
     */
    private String transactionSubjectName;
    
    /**
     * 交易类型 (1: 充值, -1: 提现, 2: 买, -2: 卖)
     */
    private Integer transactionType;
    
    /**
     * 支付方式
     */
    private String paymentMethod;
    
    /**
     * 渠道图标
     */
    private String channelIcon;
    
    /**
     * 次级渠道icon
     */
    private String subjectIcon;
    
    /**
     * 最大限额
     */
    private BigDecimal maximum;
    
    /**
     * 最小限额
     */
    private BigDecimal minimum;
    
    /**
     * 剩余每日限额
     */
    private BigDecimal dailyLimit;
    
    /**
     * 剩余每月限额
     */
    private BigDecimal monthlyLimit;
    
    /**
     * 剩余每月限额
     */
    private BigDecimal annualLimit;
    
    /**
     * 剩余每日限额
     */
    private BigDecimal remainingDailyLimit;
    
    /**
     * 剩余每月限额
     */
    private BigDecimal remainingMonthlyLimit;
    
    /**
     * 剩余每月限额
     */
    private BigDecimal remainingAnnualLimit;
    
    /**
     * 精度
     */
    private String precision;
    
    /**
     * 费用
     */
    private BigDecimal fee;
    
    /**
     * percentage/fixed 百分比或固定值
     */
    private String feeType;
    
    /**
     * 开启状态, 1开启, 0关闭
     */
    private String status;
    
    /**
     * 最大可提现余额
     */
    private BigDecimal maxWithdrawBalance;
}
