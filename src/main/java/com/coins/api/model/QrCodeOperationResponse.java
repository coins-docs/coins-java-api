package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Response for QR code operations (cancel, update, etc.)
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QrCodeOperationResponse {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("requestId")
    private String requestId;
    
    @JsonProperty("referenceId")
    private String referenceId;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("status")
    private String status;
    
    // Constructors
    public QrCodeOperationResponse() {}
    
    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getReferenceId() {
        return referenceId;
    }
    
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "QrCodeOperationResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", requestId='" + requestId + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
