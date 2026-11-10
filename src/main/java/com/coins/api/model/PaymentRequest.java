package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a payment request
 */
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("payer_contact_info")
    private String payerContactInfo;
    
    @JsonProperty("receiving_account")
    private String receivingAccount;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("supported_payment_collectors")
    private List<String> supportedPaymentCollectors;
    
    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    
    @JsonProperty("payment_url")
    private String paymentUrl;
    
    @JsonProperty("qr_code")
    private String qrCode;
    
    @JsonProperty("reference_number")
    private String referenceNumber;

    // Constructors
    public PaymentRequest() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayerContactInfo() {
        return payerContactInfo;
    }

    public void setPayerContactInfo(String payerContactInfo) {
        this.payerContactInfo = payerContactInfo;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSupportedPaymentCollectors() {
        return supportedPaymentCollectors;
    }

    public void setSupportedPaymentCollectors(List<String> supportedPaymentCollectors) {
        this.supportedPaymentCollectors = supportedPaymentCollectors;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "id='" + id + '\'' +
                ", payerContactInfo='" + payerContactInfo + '\'' +
                ", receivingAccount='" + receivingAccount + '\'' +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", supportedPaymentCollectors=" + supportedPaymentCollectors +
                ", expiresAt=" + expiresAt +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", paymentUrl='" + paymentUrl + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }
}
