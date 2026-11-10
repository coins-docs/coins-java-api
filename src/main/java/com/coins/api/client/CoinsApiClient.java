package com.coins.api.client;

import com.coins.api.client.base.ConvertClient;
import com.coins.api.client.base.FiatClient;
import com.coins.api.client.base.InvoicePaymentClient;
import com.coins.api.client.base.P2pTransferClient;
import com.coins.api.client.base.SpotTradingClient;
import com.coins.api.client.base.WalletClient;

/**
 * Main Coins API client that provides access to all API functionality
 */
public class CoinsApiClient {
    private final SpotTradingClient spotTradingClient;
    private final ConvertClient convertClient;
    private final FiatClient fiatClient;
    private final P2pTransferClient p2pTransferClient;
    private final InvoicePaymentClient paymentClient;
    private final WalletClient walletClient;

    public CoinsApiClient(CoinsApiConfig config) {
        this.spotTradingClient = new SpotTradingClient(config);
        this.convertClient = new ConvertClient(config);
        this.fiatClient = new FiatClient(config);
        this.p2pTransferClient = new P2pTransferClient(config);
        this.paymentClient = new InvoicePaymentClient(config);
        this.walletClient = new WalletClient(config);
    }

    /**
     * Get Spot Trading client
     */
    public SpotTradingClient spotTrading() {
        return spotTradingClient;
    }

    /**
     * Get Convert client
     */
    public ConvertClient convert() {
        return convertClient;
    }

    /**
     * Get Fiat client
     */
    public FiatClient fiat() {
        return fiatClient;
    }

    /**
     * Get P2P Transfer client
     */
    public P2pTransferClient p2pTransfer() {
        return p2pTransferClient;
    }

    /**
     * Get Payment client
     */
    public InvoicePaymentClient payment() {
        return paymentClient;
    }


    /**
     * Get Wallet client
     */
    public WalletClient wallet() {
        return walletClient;
    }
}
