package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferApiRequest {

    @Length(max = 100)
    @JsonProperty("client_transfer_id")
    private String clientTransferId;

    /**
     * 转出方的balance_id
     */
    @NotBlank
    private String account;

    /**
     * 转入方的email或者phone
     */
    @NotBlank
    @JsonProperty("target_address")
    private String targetAddress;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal amount;

    @Length(max = 200)
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
