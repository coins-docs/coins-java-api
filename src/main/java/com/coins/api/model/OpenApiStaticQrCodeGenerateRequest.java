package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author cary.li@coins.ph
 * @date 2025/4/16 11:14
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiStaticQrCodeGenerateRequest {

    @NotBlank(message = "requestId is not allowed to be empty.")
    @Size(min = 15, max = 36, message = "requestId  must be within 15~36 characters.")
    private String requestId;

    @NotBlank(message = "source is not allowed to be empty!")
    @Size(min = 1, max = 30, message = "source  must be within 1~30 characters.")
    private String source;

    @Size(min = 1, max = 10, message = "currency must be within 1~10 characters.QrCodeCashIntValidateManger")
    @NotBlank(message = "currency is not allowed to be empty.")
    private String currency;

    private String remark;

    /**
     * 二维码商户名称
     */
    private String qrCodeMerchantName;

    private String userId;

    private String orgId;

    private String subMerchantId;
}
