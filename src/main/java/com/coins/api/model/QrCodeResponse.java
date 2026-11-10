package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QrCodeResponse {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 二维码ID
     */
    private String qrCodeId;

    /**
     * 二维码内容
     */
    private String qrCodeContent;

    /**
     * 二维码URL
     */
    private String qrCodeUrl;

    /**
     * 二维码类型
     */
    private String type;

    /**
     * 来源
     */
    private String source;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 备注
     */
    private String remark;

    /**
     * 过期时间（秒）
     */
    private Integer expiredSeconds;

    /**
     * 商户名称
     */
    private String qrCodeMerchantName;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 过期时间
     */
    private Date expiredAt;

}
