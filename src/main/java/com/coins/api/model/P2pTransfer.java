package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a P2P transfer transaction
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class P2pTransfer {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("client_transfer_id")
    private String clientTransferId;
    
    @JsonProperty("account")
    private String account;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("target_address")
    private String targetAddress;
    
    @JsonProperty("from_address")
    private String fromAddress;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    
    @JsonProperty("fee")
    private BigDecimal fee;
    
    @JsonProperty("transaction_hash")
    private String transactionHash;
    
    @JsonProperty("exchange")
    private String exchange;
    
    @JsonProperty("payment")
    private String payment;
    
    @JsonProperty("errorMessage")
    private String errorMessage;

    // Constructors
    public P2pTransfer() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientTransferId() {
        return clientTransferId;
    }

    public void setClientTransferId(String clientTransferId) {
        this.clientTransferId = clientTransferId;
    }

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

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "P2pTransfer{" +
                "id='" + id + '\'' +
                ", clientTransferId='" + clientTransferId + '\'' +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", targetAddress='" + targetAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", fee=" + fee +
                ", transactionHash='" + transactionHash + '\'' +
                ", exchange='" + exchange + '\'' +
                ", payment='" + payment + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
