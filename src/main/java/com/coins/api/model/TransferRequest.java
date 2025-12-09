package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Request model for P2P transfer
 */
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferRequest {
    
    @JsonProperty("account")
    private String account;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("target_address")
    private String targetAddress;
    
    @JsonProperty("client_transfer_id")
    private String clientTransferId;
    
    @JsonProperty("message")
    private String message;

    // Constructors
    public TransferRequest() {}

    public TransferRequest(String account, BigDecimal amount, String targetAddress) {
        this.account = account;
        this.amount = amount;
        this.targetAddress = targetAddress;
    }

    // Getters and Setters
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getClientTransferId() {
        return clientTransferId;
    }

    public void setClientTransferId(String clientTransferId) {
        this.clientTransferId = clientTransferId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "account='" + account + '\'' +
                ", amount=" + amount +
                ", targetAddress='" + targetAddress + '\'' +
                ", clientTransferId='" + clientTransferId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
