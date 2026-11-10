package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a sell order
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellOrder {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("bank_account_number")
    private String bankAccountNumber;
    
    @JsonProperty("bank_account_name")
    private String bankAccountName;
    
    @JsonProperty("recipient_phone_number")
    private String recipientPhoneNumber;
    
    @JsonProperty("payment_outlet")
    private String paymentOutlet;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    
    @JsonProperty("exchange_rate")
    private BigDecimal exchangeRate;
    
    @JsonProperty("fee")
    private BigDecimal fee;
    
    @JsonProperty("net_amount")
    private BigDecimal netAmount;
    
    @JsonProperty("reference_number")
    private String referenceNumber;

    // Constructors
    public SellOrder() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public String getPaymentOutlet() {
        return paymentOutlet;
    }

    public void setPaymentOutlet(String paymentOutlet) {
        this.paymentOutlet = paymentOutlet;
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

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "SellOrder{" +
                "id='" + id + '\'' +
                ", baseAmount=" + baseAmount +
                ", currency='" + currency + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", recipientPhoneNumber='" + recipientPhoneNumber + '\'' +
                ", paymentOutlet='" + paymentOutlet + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", exchangeRate=" + exchangeRate +
                ", fee=" + fee +
                ", netAmount=" + netAmount +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }
}
