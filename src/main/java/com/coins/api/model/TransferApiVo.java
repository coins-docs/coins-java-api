package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferApiVo {

    private String id;

    private String status;

    private String account;

    @JsonProperty("target_address")
    private String targetAddress;

    private String amount;

    private String exchange;

    private String payment;

    @JsonProperty("client_transfer_id")
    private String clientTransferId;

    private String message;

    private String errorMessage;

    // Manual getters for compatibility
    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getAccount() {
        return account;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public String getAmount() {
        return amount;
    }

    public String getExchange() {
        return exchange;
    }

    public String getPayment() {
        return payment;
    }

    public String getClientTransferId() {
        return clientTransferId;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public enum TransferApiVOStatus {

        PENDING("pending"),

        SUCCESS("success"),

        FAILED("failed");

        private String value;

        TransferApiVOStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
