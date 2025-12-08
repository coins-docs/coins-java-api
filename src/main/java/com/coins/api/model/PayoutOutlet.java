package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Represents a payout outlet
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayoutOutlet {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("outlet_category")
    private String outletCategory;
    
    @JsonProperty("region")
    private String region;
    
    @JsonProperty("is_enabled")
    private Boolean isEnabled;
    
    @JsonProperty("ui_view")
    private String uiView;
    
    @JsonProperty("denominations")
    private List<String> denominations;
    
    @JsonProperty("min_amount")
    private BigDecimal minAmount;
    
    @JsonProperty("max_amount")
    private BigDecimal maxAmount;
    
    @JsonProperty("fee_structure")
    private Map<String, Object> feeStructure;
    
    @JsonProperty("required_fields")
    private List<String> requiredFields;

    // Constructors
    public PayoutOutlet() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutletCategory() {
        return outletCategory;
    }

    public void setOutletCategory(String outletCategory) {
        this.outletCategory = outletCategory;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getUiView() {
        return uiView;
    }

    public void setUiView(String uiView) {
        this.uiView = uiView;
    }

    public List<String> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<String> denominations) {
        this.denominations = denominations;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Map<String, Object> getFeeStructure() {
        return feeStructure;
    }

    public void setFeeStructure(Map<String, Object> feeStructure) {
        this.feeStructure = feeStructure;
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    @Override
    public String toString() {
        return "PayoutOutlet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", outletCategory='" + outletCategory + '\'' +
                ", region='" + region + '\'' +
                ", isEnabled=" + isEnabled +
                ", uiView='" + uiView + '\'' +
                ", denominations=" + denominations +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", feeStructure=" + feeStructure +
                ", requiredFields=" + requiredFields +
                '}';
    }
}
