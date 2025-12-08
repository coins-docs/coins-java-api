package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty
    @Length(min=1, max=10)
    private String coin;

    private String withdrawOrderId;

    @NotEmpty
    @Length(min=1, max=20)
    private String network;

    @NotEmpty
    @Length(min=1, max=100)
    private String address;

    private String addressTag;

    @NotNull
    @DecimalMin(value = "0", inclusive=false)
    private BigDecimal amount;
}
