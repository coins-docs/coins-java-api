package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferApiResponse {

    private TransferApiVo transfer;

    // Manual getter for compatibility
    public TransferApiVo getTransfer() {
        return transfer;
    }

    public void setTransfer(TransferApiVo transfer) {
        this.transfer = transfer;
    }
}
