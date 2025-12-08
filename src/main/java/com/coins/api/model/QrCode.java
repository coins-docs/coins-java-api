package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a QR code for fiat payments
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QrCode {
    
    @JsonProperty("requestId")
    private String requestId;
    
    @JsonProperty("referenceId")
    private String referenceId;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("source")
    private String source;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("remark")
    private String remark;
    
    @JsonProperty("expiredSeconds")
    private Integer expiredSeconds;
    
    @JsonProperty("qrCodeMerchantName")
    private String qrCodeMerchantName;
    
    @JsonProperty("qrCodeUrl")
    private String qrCodeUrl;
    
    @JsonProperty("qrCodeContent")
    private String qrCodeContent;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("createTime")
    private LocalDateTime createTime;
    
    @JsonProperty("updateTime")
    private LocalDateTime updateTime;
    
    @JsonProperty("expireTime")
    private LocalDateTime expireTime;

    // Constructors
    public QrCode() {}

    // Getters and Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getExpiredSeconds() {
        return expiredSeconds;
    }

    public void setExpiredSeconds(Integer expiredSeconds) {
        this.expiredSeconds = expiredSeconds;
    }

    public String getQrCodeMerchantName() {
        return qrCodeMerchantName;
    }

    public void setQrCodeMerchantName(String qrCodeMerchantName) {
        this.qrCodeMerchantName = qrCodeMerchantName;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getQrCodeContent() {
        return qrCodeContent;
    }

    public void setQrCodeContent(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "QrCode{" +
                "requestId='" + requestId + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", remark='" + remark + '\'' +
                ", expiredSeconds=" + expiredSeconds +
                ", qrCodeMerchantName='" + qrCodeMerchantName + '\'' +
                ", qrCodeUrl='" + qrCodeUrl + '\'' +
                ", qrCodeContent='" + qrCodeContent + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", expireTime=" + expireTime +
                '}';
    }
}
