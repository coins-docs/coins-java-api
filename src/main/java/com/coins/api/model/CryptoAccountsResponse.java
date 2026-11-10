package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Response wrapper for crypto accounts API
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoAccountsResponse {
    
    @JsonProperty("crypto-accounts")
    private List<CryptoAccount> cryptoAccounts;

    // Constructors
    public CryptoAccountsResponse() {}

    // Getters and Setters
    public List<CryptoAccount> getCryptoAccounts() {
        return cryptoAccounts;
    }

    public void setCryptoAccounts(List<CryptoAccount> cryptoAccounts) {
        this.cryptoAccounts = cryptoAccounts;
    }

    @Override
    public String toString() {
        return "CryptoAccountsResponse{" +
                "cryptoAccounts=" + cryptoAccounts +
                '}';
    }
}
