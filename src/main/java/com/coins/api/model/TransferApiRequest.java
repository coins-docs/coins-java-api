package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferApiRequest {

    @Length(max = 100, message = "clientTransferId length must not exceed 100 characters")
    @JsonProperty("client_transfer_id")
    private String clientTransferId;

    /**
     * 转出方的balance_id
     */
    @NotBlank(message = "account cannot be blank")
    private String account;

    /**
     * 转入方的email或者phone
     */
    @NotBlank(message = "targetAddress cannot be blank")
    @JsonProperty("target_address")
    private String targetAddress;

    @NotNull(message = "amount cannot be null")
    @DecimalMin(value = "0", inclusive = false, message = "amount must be greater than 0")
    private BigDecimal amount;

    @Length(max = 200, message = "message length must not exceed 200 characters")
    private String message;

    // Manual setters for compatibility
    public void setClientTransferId(String clientTransferId) {
        this.clientTransferId = clientTransferId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
