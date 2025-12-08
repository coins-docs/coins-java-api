package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * crypto-accounts of coins.ph
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceApiVo {

    private String id;

    private String name;

    private String currency;

    private String balance;

    /**
     * lock balance
     */
    @JsonProperty("pending_balance")
    private String pendingBalance;
}
