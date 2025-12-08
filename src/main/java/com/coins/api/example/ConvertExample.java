package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.AcceptQuoteRequest;
import com.coins.api.model.AcceptQuoteResponse;
import com.coins.api.model.ConvertOrderHistoryResponse;
import com.coins.api.model.ConvertQuote;
import com.coins.api.model.GetOrderHistoryRequest;
import com.coins.api.model.GetQuoteRequest;
import com.coins.api.model.GetSupportedTradingPairsRequest;
import com.coins.api.model.SupportedTradingPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Example usage of the Convert API
 */
public class ConvertExample {
    private static final Logger log = LoggerFactory.getLogger(ConvertExample.class);
    
    public static void main(String[] args) {
        CoinsApiConfig config = CoinsApiConfig.builder()
                                              .apiKey("your-api-key")
                                              .secretKey("your-secret-key")
                                              .baseUrl("https://api.pro.coins.ph")  // Use appropriate base URL
                                              .recvWindow(5000)  // Optional: request timeout window in milliseconds
                                              .build();

        CoinsApiClient client = new CoinsApiClient(config);

        // Execute Convert examples
        runConvertExamples(client);
    }

    private static void runConvertExamples(CoinsApiClient client) {
        log.info("=== Convert API Examples ===");

        try {
            // Example 1: Get supported trading pairs
            log.info("1. Getting supported trading pairs...");
            GetSupportedTradingPairsRequest tradingPairsRequest = GetSupportedTradingPairsRequest.builder()
                .type("CONVERT")
                .build();
            List<SupportedTradingPair> tradingPairs = client.convert().getSupportedTradingPairs(tradingPairsRequest);
            log.info("Found {} supported trading pairs", tradingPairs.size());
            
            // Display first few trading pairs
            int displayCount = Math.min(5, tradingPairs.size());
            for (int i = 0; i < displayCount; i++) {
                SupportedTradingPair pair = tradingPairs.get(i);
                log.info("Trading pair {}: {} -> {} (Min: {}, Max: {})", (i + 1), 
                    pair.getSourceCurrency(), pair.getTargetCurrency(), 
                    pair.getMinSourceAmount(), pair.getMaxSourceAmount());
            }
            if (tradingPairs.size() > displayCount) {
                log.info("... and {} more pairs", (tradingPairs.size() - displayCount));
            }

            // Example 2: Get conversion quote
            log.info("2. Getting conversion quote...");
            GetQuoteRequest quoteRequest = GetQuoteRequest.builder()
                .sourceCurrency("USDT")
                .targetCurrency("PHP")
                .sourceAmount("100")
                .build();
            ConvertQuote quote = client.convert().getQuote(quoteRequest);
            log.info("Quote result: {}", quote);


            log.info("3. Accepting quote and executing conversion...");
            AcceptQuoteRequest acceptRequest = AcceptQuoteRequest.builder()
                .quoteId(quote.getQuoteId())
                .build();
            AcceptQuoteResponse result = client.convert().acceptQuote(acceptRequest);
            log.info("Conversion result: {}", result);


            // Example 4: Query conversion order history
            log.info("4. Getting conversion order history...");
            GetOrderHistoryRequest historyRequest = GetOrderHistoryRequest.builder()
                .page(1)
                .size(10)
                .build();
            ConvertOrderHistoryResponse history = client.convert().queryOrderHistory(historyRequest);
            log.info("Conversion history: {}", history);
            
            // Display history details if available
            if (history.getOrders() != null) {
                List<ConvertOrderHistoryResponse.ConvertOrder> orders = history.getOrders();
                log.info("Found {} conversion orders in history", orders.size());
                
                for (int i = 0; i < Math.min(3, orders.size()); i++) {
                    ConvertOrderHistoryResponse.ConvertOrder order = orders.get(i);
                    log.info("Order {}: {} -> {} | Status: {}", (i + 1),
                                     order.getSourceCurrency(), order.getTargetCurrency(), 
                                     order.getStatus());
                }
            }

            // Example 5: Get another quote with different parameters
            log.info("5. Getting quote for PHP to USDT conversion...");
            try {
                GetQuoteRequest phpToUsdtRequest = GetQuoteRequest.builder()
                    .sourceCurrency("PHP")
                    .targetCurrency("USDT")
                    .sourceAmount("100")
                    .build();
                ConvertQuote phpToUsdtQuote = client.convert().getQuote(phpToUsdtRequest);
                log.info("PHP to USDT Quote:");
                log.info("Quote ID: {}", phpToUsdtQuote.getQuoteId());
                log.info("Source: {} {}", phpToUsdtQuote.getSourceAmount(), phpToUsdtQuote.getSourceCurrency());
                log.info("Target: {} {}", phpToUsdtQuote.getTargetAmount(), phpToUsdtQuote.getTargetCurrency());
                log.info("Price: {}", phpToUsdtQuote.getPrice());
            } catch (CoinsApiException e) {
                log.warn("Could not get PHP to USDT quote: {}", e.getMessage());
            }

            log.info("=== Convert Examples Completed ===");
            log.info("Note: Quote acceptance example is commented out to prevent accidental execution.");
            log.info("Uncomment the acceptQuote section when ready to test with real conversions.");

        } catch (CoinsApiException e) {
            log.error("Convert API Error: {} - {}", e.getCode(), e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
        }
    }
}
