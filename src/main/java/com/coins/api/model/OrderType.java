package com.coins.api.model;

/**
 * Order type enumeration
 */
public enum OrderType {
    LIMIT("limit"),
    MARKET("market"),
    STOP_LOSS("stop_loss"),
    STOP_LOSS_LIMIT("stop_loss_limit"),
    TAKE_PROFIT("take_profit"),
    TAKE_PROFIT_LIMIT("take_profit_limit");

    private final String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
