package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a fiat order
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiatOrder {
    
    @JsonProperty("internalOrderId")
    private String internalOrderId;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    @JsonProperty("channelName")
    private String channelName;
    
    @JsonProperty("channelSubject")
    private String channelSubject;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("transactionType")
    private Integer transactionType;
    
    @JsonProperty("transactionChannel")
    private String transactionChannel;
    
    @JsonProperty("transactionSubject")
    private String transactionSubject;
    
    @JsonProperty("fiatCurrency")
    private String fiatCurrency;
    
    @JsonProperty("createTime")
    private LocalDateTime createTime;
    
    @JsonProperty("updateTime")
    private LocalDateTime updateTime;
    
    @JsonProperty("extendInfo")
    private Map<String, Object> extendInfo;

    // Constructors
    public FiatOrder() {}

    // Getters and Setters
    public String getInternalOrderId() {
        return internalOrderId;
    }

    public void setInternalOrderId(String internalOrderId) {
        this.internalOrderId = internalOrderId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelSubject() {
        return channelSubject;
    }

    public void setChannelSubject(String channelSubject) {
        this.channelSubject = channelSubject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionChannel() {
        return transactionChannel;
    }

    public void setTransactionChannel(String transactionChannel) {
        this.transactionChannel = transactionChannel;
    }

    public String getTransactionSubject() {
        return transactionSubject;
    }

    public void setTransactionSubject(String transactionSubject) {
        this.transactionSubject = transactionSubject;
    }

    public String getFiatCurrency() {
        return fiatCurrency;
    }

    public void setFiatCurrency(String fiatCurrency) {
        this.fiatCurrency = fiatCurrency;
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

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public String toString() {
        return "FiatOrder{" +
                "internalOrderId='" + internalOrderId + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", channelName='" + channelName + '\'' +
                ", channelSubject='" + channelSubject + '\'' +
                ", status='" + status + '\'' +
                ", transactionType=" + transactionType +
                ", transactionChannel='" + transactionChannel + '\'' +
                ", transactionSubject='" + transactionSubject + '\'' +
                ", fiatCurrency='" + fiatCurrency + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", extendInfo=" + extendInfo +
                '}';
    }
}
