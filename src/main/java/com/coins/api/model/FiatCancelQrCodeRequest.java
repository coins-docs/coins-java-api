package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request for cancelling QR code
 */
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatCancelQrCodeRequest {
    
    @JsonProperty("referenceId")
    @NotBlank(message = "referenceId is not allowed to be null or empty")
    @Size(min = 1, max = 100, message = "referenceId must be between 1 and 100 characters")
    private String referenceId;
    
    @JsonProperty("requestId")
    @Size(max = 100, message = "requestId must not exceed 100 characters")
    private String requestId;
    
    @JsonProperty("cancelReason")
    @Size(max = 500, message = "cancelReason must not exceed 500 characters")
    private String cancelReason;
    
    @JsonProperty("recvWindow")
    private Long recvWindow;
    
    @JsonProperty("timestamp")
    private Long timestamp;
    
    // Constructors
    public FiatCancelQrCodeRequest() {}
    
    // Getters and Setters
    public String getReferenceId() {
        return referenceId;
    }
    
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getCancelReason() {
        return cancelReason;
    }
    
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
    
    public Long getRecvWindow() {
        return recvWindow;
    }
    
    public void setRecvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "FiatCancelQrCodeRequest{" +
                "referenceId='" + referenceId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", recvWindow=" + recvWindow +
                ", timestamp=" + timestamp +
                '}';
    }
}
