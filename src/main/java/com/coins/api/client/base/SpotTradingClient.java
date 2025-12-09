package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.CancelAllOrdersRequest;
import com.coins.api.model.CancelOrderRequest;
import com.coins.api.model.CancelOrderResponse;
import com.coins.api.model.CancelReplaceRequest;
import com.coins.api.model.GetOrderRequest;
import com.coins.api.model.HistoryOrdersRequest;
import com.coins.api.model.HistoryTradeRequest;
import com.coins.api.model.NewOrderRequest;
import com.coins.api.model.NewOrderResponse;
import com.coins.api.model.OpenOrdersRequest;
import com.coins.api.model.OrderResponse;
import com.coins.api.model.TradeFeeRequest;
import com.coins.api.model.TradeFeeResponse;
import com.coins.api.model.TradeVo;
import com.coins.api.util.ValidationUtil;
import com.coins.api.util.UrlBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Spot Trading API client
 */
public class SpotTradingClient {
    // API endpoint URLs
    private static final String MY_TRADES_URL = "openapi/v1/myTrades";
    private static final String TRADE_FEE_URL = "openapi/v1/asset/tradeFee";
    private static final String ORDER_TEST_URL = "openapi/v1/order/test";
    private static final String ORDER_URL = "openapi/v1/order";
    private static final String OPEN_ORDERS_URL = "openapi/v1/openOrders";
    private static final String HISTORY_ORDERS_URL = "openapi/v1/historyOrders";
    private static final String CANCEL_REPLACE_URL = "openapi/v1/order/cancelReplace";

    private final HttpClient httpClient;

    public SpotTradingClient(CoinsApiConfig config) {
        this.httpClient = new HttpClient(config);
    }

    /**
     * Get account trade list
     * 
     * @param request History trade request object
     * @return List of trades
     * @throws CoinsApiException if the API call fails
     */
    public List<TradeVo> getMyTrades(@Valid HistoryTradeRequest request) throws CoinsApiException {

        // Validate request parameters with English locale
        ValidationUtil.validate(request);
        
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("symbol", request.getSymbol())
            .addParameterIf(request.getOrderId() != null && request.getOrderId() > 0, "orderId", request.getOrderId())
            .addParameterIf(request.getStartTime() != null && request.getStartTime() > 0, "startTime", request.getStartTime())
            .addParameterIf(request.getEndTime() != null && request.getEndTime() > 0, "endTime", request.getEndTime())
            .addParameterIf(request.getFromId() != null && request.getFromId() > 0, "fromId", request.getFromId())
            .addParameterIf(request.getLimit() != null && request.getLimit() > 0, "limit", request.getLimit());
        
        return httpClient.get(MY_TRADES_URL, urlBuilder, new TypeReference<List<TradeVo>>() {});
    }

    /**
     * Get trade fee for symbols
     * 
     * @param request Trade fee request object
     * @return List of trade fees
     * @throws CoinsApiException if the API call fails
     */
    public List<TradeFeeResponse> getTradeFee(@Valid TradeFeeRequest request) throws CoinsApiException {
        if (request != null) {
            // Validate request parameters with English locale
            ValidationUtil.validate(request);
        }

        UrlBuilder urlBuilder = UrlBuilder.create("");
        if (request != null && request.getSymbol() != null && !request.getSymbol().trim().isEmpty()) {
            urlBuilder.addParameter("symbol", request.getSymbol());
        }
        
        return httpClient.get(TRADE_FEE_URL, urlBuilder, new TypeReference<List<TradeFeeResponse>>() {});
    }

    /**
     * Build UrlBuilder for new order request
     * 
     * @param request New order request object
     * @return UrlBuilder
     */
    private UrlBuilder buildNewOrderUrlBuilder(NewOrderRequest request) {
        return UrlBuilder.create("")
            .addParameter("symbol", request.getSymbol())
            .addParameter("side", request.getSide())
            .addParameter("type", request.getType())
            .addParameter("timeInForce", request.getTimeInForce())
            .addParameter("quantity", request.getQuantity())
            .addParameter("quoteOrderQty", request.getQuoteOrderQty())
            .addParameter("price", request.getPrice())
            .addParameter("newClientOrderId", request.getNewClientOrderId())
            .addParameter("stopPrice", request.getStopPrice())
            .addParameter("newOrderRespType", request.getNewOrderRespType())
            .addParameter("stpFlag", request.getStpFlag());
    }

    /**
     * Build query string for new order request
     * 
     * @param request New order request object
     * @return Query string
     */
    private String buildNewOrderQueryString(NewOrderRequest request) {
        return buildNewOrderUrlBuilder(request).buildQueryString();
    }

    /**
     * Test new order creation (validation only)
     * 
     * @param request New order request object
     * @return Empty object indicating success
     * @throws CoinsApiException if the API call fails
     */
    public Object testNewOrder(@Valid NewOrderRequest request) throws CoinsApiException {
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate request parameters with English locale
        ValidationUtil.validate(request);

        UrlBuilder urlBuilder = buildNewOrderUrlBuilder(request);
        return httpClient.post(ORDER_TEST_URL, urlBuilder, new TypeReference<Object>() {});
    }

    /**
     * Create a new order
     * 
     * @param request New order request object
     * @return New order response
     * @throws CoinsApiException if the API call fails
     */
    public NewOrderResponse newOrder(@Valid NewOrderRequest request) throws CoinsApiException {
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate request parameters with English locale
        ValidationUtil.validate(request);

        UrlBuilder urlBuilder = buildNewOrderUrlBuilder(request);
        return httpClient.post(ORDER_URL, urlBuilder, new TypeReference<NewOrderResponse>() {});
    }

    /**
     * Get history orders
     * 
     * @param request History orders request object
     * @return List of orders
     * @throws CoinsApiException if the API call fails
     */
    public List<OrderResponse> getHistoryOrders(HistoryOrdersRequest request) throws CoinsApiException {
        if (request != null) {
            ValidationUtil.validate(request);
        }
        
        UrlBuilder urlBuilder = UrlBuilder.create("");
        
        if (request != null) {
            urlBuilder.addParameterIf(request.getSymbol() != null && !request.getSymbol().trim().isEmpty(), "symbol", request.getSymbol())
                     .addParameterIf(request.getOrderId() != null && request.getOrderId() > 0, "orderId", request.getOrderId())
                     .addParameterIf(request.getStartTime() != null && request.getStartTime() > 0, "startTime", request.getStartTime())
                     .addParameterIf(request.getEndTime() != null && request.getEndTime() > 0, "endTime", request.getEndTime())
                     .addParameterIf(request.getLimit() != null, "limit", request.getLimit());
        }
        
        return httpClient.get(HISTORY_ORDERS_URL, urlBuilder, new TypeReference<List<OrderResponse>>() {});
    }

    /**
     * Get order information
     * 
     * @param request Get order request object
     * @return Order response or list of orders
     * @throws CoinsApiException if the API call fails
     */
    public Object getOrder(GetOrderRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("orderId", request.getOrderId())
            .addParameterIf(request.getOrigClientOrderId() != null && !request.getOrigClientOrderId().trim().isEmpty(), 
                           "origClientOrderId", request.getOrigClientOrderId());
        
        return httpClient.get(ORDER_URL, urlBuilder, new TypeReference<Object>() {});
    }

    /**
     * Cancel an order
     * 
     * @param request Cancel order request object
     * @return Cancel order response
     * @throws CoinsApiException if the API call fails
     */
    public CancelOrderResponse cancelOrder(CancelOrderRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("orderId", request.getOrderId())
            .addParameterIf(request.getOrigClientOrderId() != null && !request.getOrigClientOrderId().trim().isEmpty(), 
                           "origClientOrderId", request.getOrigClientOrderId());
        
        return httpClient.delete(ORDER_URL, urlBuilder, new TypeReference<CancelOrderResponse>() {});
    }

    /**
     * Get open orders
     * 
     * @param request Open orders request object
     * @return List of open orders
     * @throws CoinsApiException if the API call fails
     */
    public List<OrderResponse> getOpenOrders(OpenOrdersRequest request) throws CoinsApiException {
        UrlBuilder urlBuilder = UrlBuilder.create("");
        
        if (request != null && request.getSymbol() != null && !request.getSymbol().trim().isEmpty()) {
            urlBuilder.addParameter("symbol", request.getSymbol());
        }
        
        return httpClient.get(OPEN_ORDERS_URL, urlBuilder, new TypeReference<List<OrderResponse>>() {});
    }

    /**
     * Cancel all open orders
     * 
     * @param request Cancel all orders request object
     * @return List of cancelled orders
     * @throws CoinsApiException if the API call fails
     */
    public List<CancelOrderResponse> cancelAllOrders(CancelAllOrdersRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("symbol", request.getSymbol());
        
        return httpClient.delete(OPEN_ORDERS_URL, urlBuilder, new TypeReference<List<CancelOrderResponse>>() {});
    }

    /**
     * Build UrlBuilder for cancel replace request
     * 
     * @param request Cancel replace request object
     * @return UrlBuilder
     */
    private UrlBuilder buildCancelReplaceUrlBuilder(CancelReplaceRequest request) {
        return UrlBuilder.create("")
            .addParameter("symbol", request.getSymbol())
            .addParameter("side", request.getSide())
            .addParameter("type", request.getType())
            .addParameter("timeInForce", request.getTimeInForce())
            .addParameter("quantity", request.getQuantity())
            .addParameter("quoteOrderQty", request.getQuoteOrderQty())
            .addParameter("price", request.getPrice())
            .addParameter("newClientOrderId", request.getNewClientOrderId())
            .addParameter("stopPrice", request.getStopPrice())
            .addParameter("newOrderRespType", request.getNewOrderRespType())
            .addParameter("stpFlag", request.getStpFlag())
            .addParameter("cancelOrderId", request.getCancelOrderId())
            .addParameter("cancelReplaceMode", request.getCancelReplaceMode())
            .addParameter("cancelRestrictions", request.getCancelRestrictions())
            .addParameter("cancelOrigClientOrderId", request.getCancelOrigClientOrderId());
    }

    /**
     * Build query string for cancel replace request
     * 
     * @param request Cancel replace request object
     * @return Query string
     */
    private String buildCancelReplaceQueryString(CancelReplaceRequest request) {
        return buildCancelReplaceUrlBuilder(request).buildQueryString();
    }

    /**
     * Cancel and replace an order
     * 
     * @param request Cancel replace request object
     * @return Cancel replace response
     * @throws CoinsApiException if the API call fails
     */
    public Object cancelReplace(CancelReplaceRequest request) throws CoinsApiException {
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate required parameters
        validateCancelReplaceRequest(request);

        UrlBuilder urlBuilder = buildCancelReplaceUrlBuilder(request);
        return httpClient.post(CANCEL_REPLACE_URL, urlBuilder, new TypeReference<Object>() {});
    }

    /**
     * Validate cancel replace request parameters
     */
    private void validateCancelReplaceRequest(CancelReplaceRequest request) throws CoinsApiException {
        // Validate required fields
        if (request.getSymbol() == null || request.getSymbol().trim().isEmpty()) {
            throw new CoinsApiException("Symbol is required");
        }
        if (request.getSymbol().length() > 20) {
            throw new CoinsApiException("Symbol length cannot exceed 20 characters");
        }
        if (request.getSide() == null || request.getSide().trim().isEmpty()) {
            throw new CoinsApiException("Side is required");
        }
        if (request.getType() == null || request.getType().trim().isEmpty()) {
            throw new CoinsApiException("Type is required");
        }
        if (request.getCancelReplaceMode() == null || request.getCancelReplaceMode().trim().isEmpty()) {
            throw new CoinsApiException("CancelReplaceMode is required");
        }
        
        // Validate decimal values are positive
        if (request.getQuantity() != null && request.getQuantity().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new CoinsApiException("Quantity must be greater than 0");
        }
        if (request.getQuoteOrderQty() != null && request.getQuoteOrderQty().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new CoinsApiException("QuoteOrderQty must be greater than 0");
        }
        if (request.getPrice() != null && request.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new CoinsApiException("Price must be greater than 0");
        }
        if (request.getStopPrice() != null && request.getStopPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new CoinsApiException("StopPrice must be greater than 0");
        }
    }

}
