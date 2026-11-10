package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author nick
 * @Date
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletAssetConfigVo {
    private String coin;
    private String name;
    private boolean depositAllEnable;
    private boolean withdrawAllEnable;
    private BigDecimal free;
    private BigDecimal locked;
    private boolean isLegalMoney;
    private int transferPrecision;
    private BigDecimal transferMinQuantity;
    private List<TokenNetworkInfoVo> networkList;
}
