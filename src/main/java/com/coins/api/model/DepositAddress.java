package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a deposit address
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositAddress {
    private String coin;
    private String address;
    private String addressTag;
}
