package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.client.base.FiatClient;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.FiatCashOutRequest;
import com.coins.api.model.FiatCashOutResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Cash Out API Example
 */
@Slf4j
public class FiatCashOutExample {
    public static void main(String[] args) {

        // Create FiatClient instance
        CoinsApiConfig config = CoinsApiConfig.builder()
                .apiKey("Your API key")
                .secretKey("Your secret key")
                .baseUrl("https://api.9001.pl-qa.coinsxyz.me")
                .recvWindow(5000)
                .build();

        CoinsApiClient client = new CoinsApiClient(config);
        FiatClient fiatClient = client.fiat();

        try {
            // Prepare extended information (recipient details)
            Map<String, String> extendInfo = Map.of(
                    "recipientName", "John Doe",
                    "recipientAccountNumber", "987654321"
            );

            // Build cash out request
            FiatCashOutRequest cashOutRequest = FiatCashOutRequest.builder()
                    .internalOrderId("cashout-" + System.currentTimeMillis()) // Merchant internal order ID
                    .currency("PHP") // Currency type
                    .amount("100.00") // Withdrawal amount
                    .channelName("SWIFTPAY_PESONET") // Payment channel name
                    .channelSubject("gcash") // Channel subject
                    .extendInfo(extendInfo) // Extended information (recipient details)
                    .build();

            // Execute cash out
            FiatCashOutResponse cashOutResult = fiatClient.cashOut(cashOutRequest);

            // Handle response
            log.info("Cash out successful: {}", cashOutResult);
            log.info("Order ID: {}", cashOutResult.getInternalOrderId());

        } catch (CoinsApiException e) {
            log.error("Cash out failed: {}", e.getMessage());
            log.error("Error code: {}", e.getCode());
        }
    }
}
