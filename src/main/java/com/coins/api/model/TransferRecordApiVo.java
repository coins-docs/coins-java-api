package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRecordApiVo {

    private String id;

    @JsonProperty("client_transfer_id")
    private String clientTransferId;

    /**
     * balanceId
     */
    private String account;

    private String amount;

    @JsonProperty("fee_amount")
    private String feeAmount;

    private String currency;

    @JsonProperty("target_address")
    private String targetAddress;

    @JsonProperty("source_address")
    private String sourceAddress;

    private String payment;

    private Integer type;

    private String status;

    private String message;

    @JsonProperty("created_at")
    private Date createdAt;

    // Manual getters for compatibility
    public String getId() {
        return id;
    }

    public String getClientTransferId() {
        return clientTransferId;
    }

    public String getAccount() {
        return account;
    }

    public String getAmount() {
        return amount;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getPayment() {
        return payment;
    }

    public Integer getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
