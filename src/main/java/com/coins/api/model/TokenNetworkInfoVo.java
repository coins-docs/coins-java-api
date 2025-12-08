package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author nick
 * @Date
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenNetworkInfoVo {
    private String addressRegex;
    private String memoRegex;
    private String network;
    private String name;
    private Boolean depositEnable;
    private Integer minConfirm;
    private Integer unLockConfirm;
    private String withdrawDesc;
    private Boolean withdrawEnable;
    private BigDecimal withdrawFee;
    private BigDecimal withdrawIntegerMultiple;
    private BigDecimal withdrawMax;
    private BigDecimal withdrawMin;
    private Boolean sameAddress;

}
