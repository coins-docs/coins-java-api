package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransfersResponse {

    private List<TransferRecordApiVo> transfers;

    // Manual getter for compatibility
    public List<TransferRecordApiVo> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransferRecordApiVo> transfers) {
        this.transfers = transfers;
    }
}
