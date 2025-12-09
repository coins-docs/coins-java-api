package com.coins.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CancelReplaceRequest {

    @NotBlank(message = "symbol cannot be blank")
    @Length(max = 20, message = "symbol length must not exceed 20 characters")
    private String symbol;

    @NotBlank(message = "side cannot be blank")
    private String side;

    @NotBlank(message = "type cannot be blank")
    private String type;

    @NotBlank(message = "cancelReplaceMode cannot be blank")
    private String cancelReplaceMode;

    private String timeInForce;

    @DecimalMin(value = "0", inclusive = false, message = "quantity must be greater than 0")
    private BigDecimal quantity;

    @DecimalMin(value = "0", inclusive = false, message = "quoteOrderQty must be greater than 0")
    private BigDecimal quoteOrderQty;

    @DecimalMin(value = "0", inclusive = false, message = "price must be greater than 0")
    private BigDecimal price;

    private String newClientOrderId;

    private String cancelOrigClientOrderId;

    private Long cancelOrderId;

    @DecimalMin(value = "0", inclusive = false, message = "stopPrice must be greater than 0")
    private BigDecimal stopPrice;

    private String newOrderRespType;

    private String stpFlag;

    private String cancelRestrictions;

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCancelReplaceMode() {
        return cancelReplaceMode;
    }

    public void setCancelReplaceMode(String cancelReplaceMode) {
        this.cancelReplaceMode = cancelReplaceMode;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuoteOrderQty() {
        return quoteOrderQty;
    }

    public void setQuoteOrderQty(BigDecimal quoteOrderQty) {
        this.quoteOrderQty = quoteOrderQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNewClientOrderId() {
        return newClientOrderId;
    }

    public void setNewClientOrderId(String newClientOrderId) {
        this.newClientOrderId = newClientOrderId;
    }

    public String getCancelOrigClientOrderId() {
        return cancelOrigClientOrderId;
    }

    public void setCancelOrigClientOrderId(String cancelOrigClientOrderId) {
        this.cancelOrigClientOrderId = cancelOrigClientOrderId;
    }

    public Long getCancelOrderId() {
        return cancelOrderId;
    }

    public void setCancelOrderId(Long cancelOrderId) {
        this.cancelOrderId = cancelOrderId;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getNewOrderRespType() {
        return newOrderRespType;
    }

    public void setNewOrderRespType(String newOrderRespType) {
        this.newOrderRespType = newOrderRespType;
    }

    public String getStpFlag() {
        return stpFlag;
    }

    public void setStpFlag(String stpFlag) {
        this.stpFlag = stpFlag;
    }

    public String getCancelRestrictions() {
        return cancelRestrictions;
    }

    public void setCancelRestrictions(String cancelRestrictions) {
        this.cancelRestrictions = cancelRestrictions;
    }
}
