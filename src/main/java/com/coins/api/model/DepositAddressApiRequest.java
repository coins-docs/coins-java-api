package com.coins.api.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
public class DepositAddressApiRequest {

    @NotEmpty(message = "coin cannot be empty")
    @Length(min=1, max=10, message = "coin length must be between 1 and 10 characters")
    private String coin;

    @NotEmpty(message = "network cannot be empty")
    @Length(min=1, max=20, message = "network length must be between 1 and 20 characters")
    private String network;

}
