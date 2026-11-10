package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Response wrapper for P2P transfer API
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class P2pTransferResponse {
    
    @JsonProperty("transfer")
    private P2pTransfer transfer;

    // Constructors
    public P2pTransferResponse() {}

    // Getters and Setters
    public P2pTransfer getTransfer() {
        return transfer;
    }

    public void setTransfer(P2pTransfer transfer) {
        this.transfer = transfer;
    }

    @Override
    public String toString() {
        return "P2pTransferResponse{" +
                "transfer=" + transfer +
                '}';
    }
}
