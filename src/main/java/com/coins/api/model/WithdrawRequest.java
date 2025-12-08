package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request model for withdrawal application
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawRequest {
    
    @JsonProperty("coin")
    private String coin;
    
    @JsonProperty("network")
    private String network;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("addressTag")
    private String addressTag;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("withdrawOrderId")
    private String withdrawOrderId;

    // Constructors
    public WithdrawRequest() {}

    public WithdrawRequest(String coin, String network, String address, BigDecimal amount) {
        this.coin = coin;
        this.network = network;
        this.address = address;
        this.amount = amount;
    }

    // Getters and Setters
    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressTag() {
        return addressTag;
    }

    public void setAddressTag(String addressTag) {
        this.addressTag = addressTag;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getWithdrawOrderId() {
        return withdrawOrderId;
    }

    public void setWithdrawOrderId(String withdrawOrderId) {
        this.withdrawOrderId = withdrawOrderId;
    }

    @Override
    public String toString() {
        return "WithdrawRequest{" +
                "coin='" + coin + '\'' +
                ", network='" + network + '\'' +
                ", address='" + address + '\'' +
                ", addressTag='" + addressTag + '\'' +
                ", amount=" + amount +
                ", withdrawOrderId='" + withdrawOrderId + '\'' +
                '}';
    }
}
