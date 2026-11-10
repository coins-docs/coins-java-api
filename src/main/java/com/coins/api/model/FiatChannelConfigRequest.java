package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatChannelConfigRequest {
    
    /**
     * 类型 （1. cash-in, -1 cash-out）
     */
    @NotBlank(message = "transactionType is not allowed to be empty!")
    private String transactionType;
    
    /**
     * 币种
     */
    @NotBlank(message = "currency is not allowed to be empty!")
    private String currency;
    
    /**
     * 渠道
     */
    private String transactionChannel;
    
    /**
     * 次级渠道
     */
    private String transactionSubject;
    
    /**
     * 提现金额
     */
    private String amount;

}
