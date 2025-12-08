package com.coins.api.example;

import com.coins.api.client.CoinsApiClient;
import com.coins.api.client.CoinsApiConfig;
import com.coins.api.client.base.SpotTradingClient;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Example usage of the Spot Trading API
 */
public class SpotTradingExample {
    private static final Logger log = LoggerFactory.getLogger(SpotTradingExample.class);

    public static void main(String[] args) {
        // Configure the API client
        CoinsApiConfig config = CoinsApiConfig.builder()
                                              .apiKey("yjpNCpBFzMDrQKKQUChq4YhsFwnKZiNSE4Awmqn53wUPgziaIw3OerWoxeqgh0hu")
                                              .secretKey("sWCboo8ba1LItH2bLD1AQwGnbM9gsLspJ8AlwsoCPGLMSOi3RJ96AONhl2anSGdt")
                                              .baseUrl("https://api.9001.pl-qa.coinsxyz.me")
                                              .recvWindow(5000)
                                              .build();



        CoinsApiClient client = new CoinsApiClient(config);
        SpotTradingClient spotTradingClient = client.spotTrading();

        try {
            // Example 1: Get my trades
            getMyTradesExample(spotTradingClient);
            
            // Example 2: Get trade fee
            getTradeFeeExample(spotTradingClient);
            
            // Example 3: Test new order (validation only)
            testNewOrderExample(spotTradingClient);
            
            // Example 4: Create new order and use the order ID for subsequent operations
            Long orderId = createOrderForExamples(spotTradingClient);
            
            // Example 5: Get order information (using created order ID)
            getOrderExample(spotTradingClient);
            
            // Example 6: Get history orders
            getHistoryOrdersExample(spotTradingClient);
            
            // Example 7: Get open orders
            getOpenOrdersExample(spotTradingClient);
            
            // Example 8: Cancel order (using created order ID)
            cancelOrderExample(spotTradingClient, orderId);
            
            // Example 9: Cancel all orders
            cancelAllOrdersExample(spotTradingClient);
            
            // Example 10: Cancel and replace order (create new order first)
            cancelReplaceExample(spotTradingClient);
            
        } catch (CoinsApiException e) {
            log.error("API Error: {}", e.getMessage(), e);
        }
    }

    /**
     * Create an order for use in examples
     */
    private static Long createOrderForExamples(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Creating Order for Examples ===");
        
        NewOrderRequest request = new NewOrderRequest();
        request.setSymbol("BTCPHP");
        request.setSide(OrderSide.BUY.name());
        request.setType(OrderType.LIMIT.name());
        request.setTimeInForce("GTC");
        request.setQuantity(new BigDecimal("0.001"));
        request.setPrice(new BigDecimal("5492410"));
        request.setNewClientOrderId("example-order-" + System.currentTimeMillis());

        NewOrderResponse response = spotTradingClient.newOrder(request);
        log.info("Created order with ID: {}", response.getOrderId());
        return response.getOrderId();
    }

    /**
     * Example: Get trade fee for symbols
     */
    private static void getTradeFeeExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Get Trade Fee Example ===");

        TradeFeeRequest request = new TradeFeeRequest();
        request.setSymbol("BTCPHP");
        
        List<TradeFeeResponse> tradeFees = spotTradingClient.getTradeFee(request);
        log.info("Trade fees: {}", tradeFees);
    }

    /**
     * Example: Test new order creation (validation only)
     */
    private static void testNewOrderExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Test New Order Example ===");
        
        NewOrderRequest request = new NewOrderRequest();
        request.setSymbol("BTCPHP");
        request.setSide(OrderSide.BUY.name());
        request.setType(OrderType.LIMIT.name());
        request.setTimeInForce("GTC");
        request.setQuantity(new BigDecimal("0.001"));
        request.setPrice(new BigDecimal("2000000"));
        
        Object result = spotTradingClient.testNewOrder(request);
        log.info("Test order result: {}", result);
    }

    /**
     * Example: Create a new order
     */
    private static void newOrderExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== New Order Example ===");
        
        NewOrderRequest request = new NewOrderRequest();
        request.setSymbol("BTCPHP");
        request.setSide(OrderSide.BUY.name());
        request.setType(OrderType.LIMIT.name());
        request.setTimeInForce("GTC");
        request.setQuantity(new BigDecimal("0.001"));
        request.setPrice(new BigDecimal("5492410"));
        request.setNewClientOrderId("my-order-" + System.currentTimeMillis());
        
        NewOrderResponse response = spotTradingClient.newOrder(request);
        log.info("New order response: {}", response);
    }

    /**
     * Example: Get order information
     */
    private static void getOrderExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Get Order Example ===");
        Long orderId = createOrderForExamples(spotTradingClient);

        GetOrderRequest request = new GetOrderRequest();
        request.setOrderId(orderId);
        // Or use client order ID: request.setOrigClientOrderId("my-order-123");
        
        Object orderInfo = spotTradingClient.getOrder(request);
        log.info("Order info: {}", orderInfo);
    }

    /**
     * Example: Get history orders
     */
    private static void getHistoryOrdersExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Get History Orders Example ===");
        
        HistoryOrdersRequest request = new HistoryOrdersRequest();
        request.setSymbol("BTCPHP");
        request.setLimit(10);
        
        List<OrderResponse> historyOrders = spotTradingClient.getHistoryOrders(request);
        log.info("History orders: {}", historyOrders);
    }

    /**
     * Example: Get open orders
     */
    private static void getOpenOrdersExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Get Open Orders Example ===");
        
        OpenOrdersRequest request = new OpenOrdersRequest();
        request.setSymbol("BTCPHP");
        
        List<OrderResponse> openOrders = spotTradingClient.getOpenOrders(request);
        log.info("Open orders: {}", openOrders);
    }

    /**
     * Example: Cancel an order
     */
    private static void cancelOrderExample(SpotTradingClient spotTradingClient, Long orderId) throws CoinsApiException {
        log.info("=== Cancel Order Example ===");
        
        CancelOrderRequest request = new CancelOrderRequest();
        request.setOrderId(orderId);
        // Or use client order ID: request.setOrigClientOrderId("my-order-123");
        
        CancelOrderResponse response = spotTradingClient.cancelOrder(request);
        log.info("Cancel order response: {}", response);
    }

    /**
     * Example: Cancel all open orders
     */
    private static void cancelAllOrdersExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Cancel All Orders Example ===");

        Long orderId = createOrderForExamples(spotTradingClient);


        CancelAllOrdersRequest request = new CancelAllOrdersRequest();
        request.setSymbol("BTCPHP");
        
        List<CancelOrderResponse> responses = spotTradingClient.cancelAllOrders(request);
        log.info("Cancel all orders responses: {}", responses);
    }

    /**
     * Example: Cancel and replace an order
     */
    private static void cancelReplaceExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Cancel Replace Order Example ===");
        Long orderId = createOrderForExamples(spotTradingClient);

        CancelReplaceRequest request = new CancelReplaceRequest();
        request.setSymbol("BTCPHP");
        request.setSide("BUY");
        request.setType("LIMIT");
        request.setCancelReplaceMode("STOP_ON_FAILURE");
        request.setCancelOrderId(orderId);
        request.setQuantity(new BigDecimal("0.002"));
        request.setPrice(new BigDecimal("5492210"));
        request.setTimeInForce("GTC");

        Object response = spotTradingClient.cancelReplace(request);
        log.info("Cancel replace response: {}", response);
    }

    /**
     * Example: Get my trades
     */
    private static void getMyTradesExample(SpotTradingClient spotTradingClient) throws CoinsApiException {
        log.info("=== Get My Trades Example ===");
        
        HistoryTradeRequest request = new HistoryTradeRequest();
        request.setSymbol("BTCPHP");
        request.setLimit(10);
        // Optional parameters:
        // request.setOrderId(12345L);
        // request.setStartTime(System.currentTimeMillis() - 86400000L); // 24 hours ago
        // request.setEndTime(System.currentTimeMillis());
        // request.setFromId(1L);
        
        List<TradeVo> myTrades = spotTradingClient.getMyTrades(request);
        log.info("My trades: {}", myTrades);
    }
}
