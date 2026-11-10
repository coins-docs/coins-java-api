package com.coins.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class OpenApiQrCodeGenerateResponse {

    /**
     * qr code
     */
    private String qrCode;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * coins 生成的 orderId
     */
    private String referenceId;

    /**
     * 失效时间戳
     */
    private String expiredTime;
    /**
     * 金额
     */
    private BigDecimal amount;

    private String currency;

    private String status;

    private String errorCode;

    private String errorMsg;

    private String remark;

    /**
     * 银行账户号
     */
    private String channelInvoiceNo;

    /**
     * cash-in 渠道来源
     */
    private String cashInBank;
    /**
     * 完成时间
     */
    private Long settleDate;
}
