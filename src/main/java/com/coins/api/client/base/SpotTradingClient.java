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
import com.fasterxml.jackson.core.type.TypeReference;

import javax.validation.Valid;
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
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate request parameters with English locale
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("symbol=").append(request.getSymbol());
        
        if (request.getOrderId() != null && request.getOrderId() > 0) {
            queryBuilder.append("&orderId=").append(request.getOrderId());
        }
        if (request.getStartTime() != null && request.getStartTime() > 0) {
            queryBuilder.append("&startTime=").append(request.getStartTime());
        }
        if (request.getEndTime() != null && request.getEndTime() > 0) {
            queryBuilder.append("&endTime=").append(request.getEndTime());
        }
        if (request.getFromId() != null && request.getFromId() > 0) {
            queryBuilder.append("&fromId=").append(request.getFromId());
        }
        if (request.getLimit() != null && request.getLimit() > 0) {
            queryBuilder.append("&limit=").append(request.getLimit());
        }
        
        return httpClient.get(MY_TRADES_URL, queryBuilder.toString(), new TypeReference<List<TradeVo>>() {});
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
            ValidationUtil.validate(request, Locale.ENGLISH);
        }

        StringBuilder queryBuilder = new StringBuilder();
        if (request != null && request.getSymbol() != null && !request.getSymbol().trim().isEmpty()) {
            queryBuilder.append("symbol=").append(request.getSymbol());
        }
        
        return httpClient.get(TRADE_FEE_URL, queryBuilder.toString(), new TypeReference<List<TradeFeeResponse>>() {});
    }

    /**
     * Build query string for new order request
     * 
     * @param request New order request object
     * @return Query string
     */
    private String buildNewOrderQueryString(NewOrderRequest request) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("symbol=").append(request.getSymbol());
        queryString.append("&side=").append(request.getSide());
        queryString.append("&type=").append(request.getType());

        if (request.getTimeInForce() != null) {
            queryString.append("&timeInForce=").append(request.getTimeInForce());
        }
        if (request.getQuantity() != null) {
            queryString.append("&quantity=").append(request.getQuantity());
        }
        if (request.getQuoteOrderQty() != null) {
            queryString.append("&quoteOrderQty=").append(request.getQuoteOrderQty());
        }
        if (request.getPrice() != null) {
            queryString.append("&price=").append(request.getPrice());
        }
        if (request.getNewClientOrderId() != null) {
            queryString.append("&newClientOrderId=").append(request.getNewClientOrderId());
        }
        if (request.getStopPrice() != null) {
            queryString.append("&stopPrice=").append(request.getStopPrice());
        }
        if (request.getNewOrderRespType() != null) {
            queryString.append("&newOrderRespType=").append(request.getNewOrderRespType());
        }
        if (request.getStpFlag() != null) {
            queryString.append("&stpFlag=").append(request.getStpFlag());
        }
        
        return queryString.toString();
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
        ValidationUtil.validate(request, Locale.ENGLISH);

        String queryString = buildNewOrderQueryString(request);
        return httpClient.post(ORDER_TEST_URL, queryString, new TypeReference<Object>() {});
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
        ValidationUtil.validate(request, Locale.ENGLISH);

        String queryString = buildNewOrderQueryString(request);
        return httpClient.post(ORDER_URL, queryString, new TypeReference<NewOrderResponse>() {});
    }

    /**
     * Get history orders
     * 
     * @param request History orders request object
     * @return List of orders
     * @throws CoinsApiException if the API call fails
     */
    public List<OrderResponse> getHistoryOrders(HistoryOrdersRequest request) throws CoinsApiException {
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request != null) {
            if (request.getSymbol() != null && !request.getSymbol().trim().isEmpty()) {
                queryBuilder.append("symbol=").append(request.getSymbol());
                hasParams = true;
            }
            if (request.getOrderId() != null && request.getOrderId() > 0) {
                if (hasParams) queryBuilder.append("&");
                queryBuilder.append("orderId=").append(request.getOrderId());
                hasParams = true;
            }
            if (request.getStartTime() != null && request.getStartTime() > 0) {
                if (hasParams) queryBuilder.append("&");
                queryBuilder.append("startTime=").append(request.getStartTime());
                hasParams = true;
            }
            if (request.getEndTime() != null && request.getEndTime() > 0) {
                if (hasParams) queryBuilder.append("&");
                queryBuilder.append("endTime=").append(request.getEndTime());
                hasParams = true;
            }
            if (request.getLimit() != null) {
                // Validate limit range
                if (request.getLimit() < 1 || request.getLimit() > 1000) {
                    throw new CoinsApiException("Limit must be between 1 and 1000");
                }
                if (hasParams) queryBuilder.append("&");
                queryBuilder.append("limit=").append(request.getLimit());
            }
        }
        
        return httpClient.get(HISTORY_ORDERS_URL, queryBuilder.toString(), new TypeReference<List<OrderResponse>>() {});
    }

    /**
     * Get order information
     * 
     * @param request Get order request object
     * @return Order response or list of orders
     * @throws CoinsApiException if the API call fails
     */
    public Object getOrder(GetOrderRequest request) throws CoinsApiException {
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate that at least one parameter is provided
        if (request.getOrderId() == null && (request.getOrigClientOrderId() == null || request.getOrigClientOrderId().trim().isEmpty())) {
            throw new CoinsApiException("Either orderId or origClientOrderId is required");
        }
        
        // Validate orderId range if provided
        if (request.getOrderId() != null && request.getOrderId() < 1) {
            throw new CoinsApiException("OrderId must be greater than 0");
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request.getOrderId() != null) {
            queryBuilder.append("orderId=").append(request.getOrderId());
            hasParams = true;
        }
        if (request.getOrigClientOrderId() != null && !request.getOrigClientOrderId().trim().isEmpty()) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("origClientOrderId=").append(request.getOrigClientOrderId());
        }
        
        return httpClient.get(ORDER_URL, queryBuilder.toString(), new TypeReference<Object>() {});
    }

    /**
     * Cancel an order
     * 
     * @param request Cancel order request object
     * @return Cancel order response
     * @throws CoinsApiException if the API call fails
     */
    public CancelOrderResponse cancelOrder(CancelOrderRequest request) throws CoinsApiException {
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate that at least one parameter is provided
        if (request.getOrderId() == null && (request.getOrigClientOrderId() == null || request.getOrigClientOrderId().trim().isEmpty())) {
            throw new CoinsApiException("Either orderId or origClientOrderId is required");
        }
        
        // Validate orderId range if provided
        if (request.getOrderId() != null && request.getOrderId() < 1) {
            throw new CoinsApiException("OrderId must be greater than 0");
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        boolean hasParams = false;
        
        if (request.getOrderId() != null) {
            queryBuilder.append("orderId=").append(request.getOrderId());
            hasParams = true;
        }
        if (request.getOrigClientOrderId() != null && !request.getOrigClientOrderId().trim().isEmpty()) {
            if (hasParams) queryBuilder.append("&");
            queryBuilder.append("origClientOrderId=").append(request.getOrigClientOrderId());
        }
        
        return httpClient.delete(ORDER_URL, queryBuilder.toString(), new TypeReference<CancelOrderResponse>() {});
    }

    /**
     * Get open orders
     * 
     * @param request Open orders request object
     * @return List of open orders
     * @throws CoinsApiException if the API call fails
     */
    public List<OrderResponse> getOpenOrders(OpenOrdersRequest request) throws CoinsApiException {
        StringBuilder queryBuilder = new StringBuilder();
        
        if (request != null && request.getSymbol() != null && !request.getSymbol().trim().isEmpty()) {
            queryBuilder.append("symbol=").append(request.getSymbol());
        }
        
        return httpClient.get(OPEN_ORDERS_URL, queryBuilder.toString(), new TypeReference<List<OrderResponse>>() {});
    }

    /**
     * Cancel all open orders
     * 
     * @param request Cancel all orders request object
     * @return List of cancelled orders
     * @throws CoinsApiException if the API call fails
     */
    public List<CancelOrderResponse> cancelAllOrders(CancelAllOrdersRequest request) throws CoinsApiException {
        if (request == null) {
            throw new CoinsApiException("Request cannot be null");
        }
        
        // Validate required symbol parameter
        if (request.getSymbol() == null || request.getSymbol().trim().isEmpty()) {
            throw new CoinsApiException("Symbol is required");
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("symbol=").append(request.getSymbol());
        
        return httpClient.delete(OPEN_ORDERS_URL, queryBuilder.toString(), new TypeReference<List<CancelOrderResponse>>() {});
    }

    /**
     * Build query string for cancel replace request
     * 
     * @param request Cancel replace request object
     * @return Query string
     */
    private String buildCancelReplaceQueryString(CancelReplaceRequest request) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("symbol=").append(request.getSymbol());
        queryString.append("&side=").append(request.getSide());
        queryString.append("&type=").append(request.getType());

        if (request.getTimeInForce() != null) {
            queryString.append("&timeInForce=").append(request.getTimeInForce());
        }
        if (request.getQuantity() != null) {
            queryString.append("&quantity=").append(request.getQuantity());
        }
        if (request.getQuoteOrderQty() != null) {
            queryString.append("&quoteOrderQty=").append(request.getQuoteOrderQty());
        }
        if (request.getPrice() != null) {
            queryString.append("&price=").append(request.getPrice());
        }
        if (request.getNewClientOrderId() != null) {
            queryString.append("&newClientOrderId=").append(request.getNewClientOrderId());
        }
        if (request.getStopPrice() != null) {
            queryString.append("&stopPrice=").append(request.getStopPrice());
        }
        if (request.getNewOrderRespType() != null) {
            queryString.append("&newOrderRespType=").append(request.getNewOrderRespType());
        }
        if (request.getStpFlag() != null) {
            queryString.append("&stpFlag=").append(request.getStpFlag());
        }
        
        // Cancel replace specific parameters
        if (request.getCancelOrderId() != null) {
            queryString.append("&cancelOrderId=").append(request.getCancelOrderId());
        }
        if (request.getCancelReplaceMode() != null) {
            queryString.append("&cancelReplaceMode=").append(request.getCancelReplaceMode());
        }
        if (request.getCancelRestrictions() != null) {
            queryString.append("&cancelRestrictions=").append(request.getCancelRestrictions());
        }
        if (request.getCancelOrigClientOrderId() != null) {
            queryString.append("&cancelOrigClientOrderId=").append(request.getCancelOrigClientOrderId());
        }
        
        return queryString.toString();
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

        String queryString = buildCancelReplaceQueryString(request);
        return httpClient.post(CANCEL_REPLACE_URL, queryString, new TypeReference<Object>() {});
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
