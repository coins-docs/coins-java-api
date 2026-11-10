package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cary.li@coins.ph
 * @date 2025/4/16 18:07
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiUpdateQrCodeResponse {

    /**
     * 请求id
     */
    private String requestId;

    private String referenceId;

    private String type;

    private String qrCodeMerchantName;

    private String status;
}
