package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cary.li@coins.ph
 * @date 2025/4/17 16:39
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiQrCodeResponse {

    private String requestId;

    private String qrCode;


    private String status;

    private String qrCodeMerchantName;
}
