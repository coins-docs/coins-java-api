package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountInfoResponse {
    private Boolean canTrade;
    private Boolean canDeposit;
    private Boolean canWithdraw;
    private String accountType;
    private long updateTime;
    private List<BalanceSimVo> balances;
    private String token;
    private String email;
    private Boolean enableWithdrawWhitelist;
    private KycLimitRemainingVo.RemainingDetail daily;
    private KycLimitRemainingVo.RemainingDetail monthly;
    private KycLimitRemainingVo.RemainingDetail annually;

}
