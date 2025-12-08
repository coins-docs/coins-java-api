package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sandy
 * @date 2025-06-17
 * @desc Cancel QR Code Response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiCancelQrCodeResponse {

    /**
     * Request status: SUCCEEDED, FAILED
     */
    private String requestStatus;

    /**
     * The requestId you passed in when generating the QR code
     */
    private String requestId;

    /**
     * Coins generate reference id
     */
    private String referenceId;

    /**
     * QR code status: PENDING/SUCCEEDED/FAILED/EXPIRED/CANCELED/REFUNDED
     */
    private String qrcodeStatus;

    /**
     * The request sent time
     */
    private Long createdTime;

    /**
     * Response/callback sent time
     */
    private Long updatedTime;

    /**
     * Completion time
     */
    private Long completionTime;

    /**
     * Error code
     */
    private String errorCode;

    /**
     * Error message
     */
    private String errorMsg;
}
