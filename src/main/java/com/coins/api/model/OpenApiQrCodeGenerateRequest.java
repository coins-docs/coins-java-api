package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @author sandy
 * @date 2023-11-14
 * @desc OpenApiQrCodeGenerateRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiQrCodeGenerateRequest {

    @NotBlank(message = "requestId is not allowed to be empty.")
    @Size(min = 15, max = 36, message = "requestId  must be within 15~19 characters.")
    private String requestId;

    private String type;

    @NotBlank(message = "source is not allowed to be empty!")
    @Size(min = 1, max = 30, message = "source  must be within 1~30 characters.")
    private String source;

    @Min(value = 1, message = "amount cannot be less than 1.")
    @NotNull(message = "amount is not allowed to be empty.")
    private BigDecimal amount;

    @Size(min = 1, max = 10, message = "currency must be within 1~10 characters.QrCodeCashIntValidateManger")
    @NotBlank(message = "currency is not allowed to be empty.")
    private String currency;

    private String remark;

    private String expiredSeconds;

    /**
     * 二维码商户名称
     */
    private String qrCodeMerchantName;

    private String userId;

    private String orgId;

    /**
     * external merchant id
     */
    private String subMerchantId;
}
