package com.coins.api.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class DepositAddressApiRequest {

    @NotEmpty
    @Length(min=1, max=10)
    private String coin;

    @NotEmpty
    @Length(min=1, max=20)
    private String network;

}
