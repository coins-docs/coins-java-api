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
public class TransactionHistoryVo {

    private String txId;

    private String bizSubject;

    private String tokenId;

    private String status;

    private BigDecimal changed;

    private Long time;

}
