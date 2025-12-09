package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawApplyRequest {

    @NotEmpty(message = "coin cannot be empty")
    @Length(min=1, max=10, message = "coin length must be between 1 and 10 characters")
    private String coin;

    private String withdrawOrderId;

    @NotEmpty(message = "network cannot be empty")
    @Length(min=1, max=20, message = "network length must be between 1 and 20 characters")
    private String network;

    @NotEmpty(message = "address cannot be empty")
    @Length(min=1, max=100, message = "address length must be between 1 and 100 characters")
    private String address;

    private String addressTag;

    @NotNull(message = "amount cannot be null")
    @DecimalMin(value = "0", inclusive=false, message = "amount must be greater than 0")
    private BigDecimal amount;
}
