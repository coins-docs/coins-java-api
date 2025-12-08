package com.coins.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author nick
 * @Date
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRecordVo {

    private String id;
    private BigDecimal amount;
    private String coin;
    private String network;
    private int status;
    private String address;
    private String addressTag;
    private String txId;
    private long insertTime;
    private Integer confirmNo;
}
