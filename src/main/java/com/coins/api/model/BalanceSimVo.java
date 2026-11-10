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
public class BalanceSimVo {
    private String asset;
    private BigDecimal free;
    private BigDecimal locked;
}
