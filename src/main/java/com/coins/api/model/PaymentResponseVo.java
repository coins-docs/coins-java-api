package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponseVo {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("invoice")
    private String invoice;
    
    @JsonProperty("amount")
    private String amount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("created_at")
    private Long createdAt;
    
    @JsonProperty("updated_at")
    private Long updatedAt;
    
    @JsonProperty("expires_at")
    private Long expiresAt;
    
    @JsonProperty("supported_payment_collectors")
    private String supportedPaymentCollectors;
    
    @JsonProperty("payment_url")
    private String paymentUrl;
    
    private String message;
    
    @JsonProperty("payer_contact_info")
    private String payerContactInfo;
}
