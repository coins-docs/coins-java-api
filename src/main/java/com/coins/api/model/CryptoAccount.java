package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Represents a cryptocurrency account
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoAccount {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("balance")
    private BigDecimal balance;
    
    @JsonProperty("pending_balance")
    private BigDecimal pendingBalance;
    
    @JsonProperty("available")
    private BigDecimal available;
    
    @JsonProperty("locked")
    private BigDecimal locked;

    // Constructors
    public CryptoAccount() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPendingBalance() {
        return pendingBalance;
    }

    public void setPendingBalance(BigDecimal pendingBalance) {
        this.pendingBalance = pendingBalance;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getLocked() {
        return locked;
    }

    public void setLocked(BigDecimal locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "CryptoAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", pendingBalance=" + pendingBalance +
                ", available=" + available +
                ", locked=" + locked +
                '}';
    }
}
