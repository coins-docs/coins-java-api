package com.coins.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressWhitelistVo {
    private String addressName;
    private String address;
    private String addressTag;
    private String coin;
    private String network;
}
