package com.coins.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a wallet account
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletAccount {
    
    @JsonProperty("makerCommission")
    private Integer makerCommission;
    
    @JsonProperty("takerCommission")
    private Integer takerCommission;
    
    @JsonProperty("buyerCommission")
    private Integer buyerCommission;
    
    @JsonProperty("sellerCommission")
    private Integer sellerCommission;
    
    @JsonProperty("canTrade")
    private Boolean canTrade;
    
    @JsonProperty("canWithdraw")
    private Boolean canWithdraw;
    
    @JsonProperty("canDeposit")
    private Boolean canDeposit;
    
    @JsonProperty("updateTime")
    private Long updateTime;
    
    @JsonProperty("accountType")
    private String accountType;
    
    @JsonProperty("balances")
    private List<Balance> balances;
    
    @JsonProperty("permissions")
    private List<String> permissions;

    // Constructors
    public WalletAccount() {}

    // Getters and Setters
    public Integer getMakerCommission() {
        return makerCommission;
    }

    public void setMakerCommission(Integer makerCommission) {
        this.makerCommission = makerCommission;
    }

    public Integer getTakerCommission() {
        return takerCommission;
    }

    public void setTakerCommission(Integer takerCommission) {
        this.takerCommission = takerCommission;
    }

    public Integer getBuyerCommission() {
        return buyerCommission;
    }

    public void setBuyerCommission(Integer buyerCommission) {
        this.buyerCommission = buyerCommission;
    }

    public Integer getSellerCommission() {
        return sellerCommission;
    }

    public void setSellerCommission(Integer sellerCommission) {
        this.sellerCommission = sellerCommission;
    }

    public Boolean getCanTrade() {
        return canTrade;
    }

    public void setCanTrade(Boolean canTrade) {
        this.canTrade = canTrade;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "WalletAccount{" +
                "makerCommission=" + makerCommission +
                ", takerCommission=" + takerCommission +
                ", buyerCommission=" + buyerCommission +
                ", sellerCommission=" + sellerCommission +
                ", canTrade=" + canTrade +
                ", canWithdraw=" + canWithdraw +
                ", canDeposit=" + canDeposit +
                ", updateTime=" + updateTime +
                ", accountType='" + accountType + '\'' +
                ", balances=" + balances +
                ", permissions=" + permissions +
                '}';
    }

    /**
     * Represents a balance within a wallet account
     */
    public static class Balance {
        @JsonProperty("asset")
        private String asset;
        
        @JsonProperty("free")
        private BigDecimal free;
        
        @JsonProperty("locked")
        private BigDecimal locked;

        // Constructors
        public Balance() {}

        // Getters and Setters
        public String getAsset() {
            return asset;
        }

        public void setAsset(String asset) {
            this.asset = asset;
        }

        public BigDecimal getFree() {
            return free;
        }

        public void setFree(BigDecimal free) {
            this.free = free;
        }

        public BigDecimal getLocked() {
            return locked;
        }

        public void setLocked(BigDecimal locked) {
            this.locked = locked;
        }

        @Override
        public String toString() {
            return "Balance{" +
                    "asset='" + asset + '\'' +
                    ", free=" + free +
                    ", locked=" + locked +
                    '}';
        }
    }
}
