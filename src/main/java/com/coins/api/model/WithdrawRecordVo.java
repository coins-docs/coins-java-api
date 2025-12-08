package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawRecordVo {

    private String id;
    private BigDecimal amount;
    private BigDecimal transactionFee;
    private String coin;
    private int status;
    private String address;
    private String addressTag;
    private String txId;
    private long applyTime;
    private String network;
    private String withdrawOrderId;
    private String info;
    private int confirmNo;

}