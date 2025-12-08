package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cary.li@coins.ph
 * @date 2025/4/23 16:46
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiStaticQrCodeGenResponse {

    private String qrCode;

    private String requestId;

    private String referenceId;


    private String status;

    private String qrCodeType;
}
