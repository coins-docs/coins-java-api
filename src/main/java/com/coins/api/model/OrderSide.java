package com.coins.api.model;

/**
 * Order side enumeration
 */
public enum OrderSide {
    BUY("buy"),
    SELL("sell");

    private final String value;

    OrderSide(String value) {
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
