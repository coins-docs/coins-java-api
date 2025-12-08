package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.AcceptQuoteRequest;
import com.coins.api.model.AcceptQuoteResponse;
import com.coins.api.model.ConvertOrderHistoryResponse;
import com.coins.api.model.ConvertQuote;
import com.coins.api.model.GetOrderHistoryRequest;
import com.coins.api.model.GetQuoteRequest;
import com.coins.api.model.GetQuoteResponse;
import com.coins.api.model.GetSupportedTradingPairsRequest;
import com.coins.api.model.GetSupportedTradingPairsResponse;
import com.coins.api.model.SupportedTradingPair;
import com.coins.api.util.ValidationUtil;
import com.coins.api.util.UrlBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Locale;

/**
 * Convert API client
 */
public class ConvertClient {
    // API endpoint URLs
    private static final String SUPPORTED_TRADING_PAIRS_URL = "openapi/convert/v1/get-supported-trading-pairs";
    private static final String GET_QUOTE_URL = "openapi/convert/v1/get-quote";
    private static final String ACCEPT_QUOTE_URL = "openapi/convert/v1/accept-quote";
    private static final String QUERY_ORDER_HISTORY_URL = "openapi/convert/v1/query-order-history";

    private final HttpClient httpClient;

    public ConvertClient(CoinsApiConfig config) {
        this.httpClient = new HttpClient(config);
    }

    /**
     * Get supported trading pairs (get-supported-trading-pairs)
     */
    public List<SupportedTradingPair> getSupportedTradingPairs(GetSupportedTradingPairsRequest request) throws CoinsApiException {

        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("type", request.getType());

        GetSupportedTradingPairsResponse response = httpClient.post(SUPPORTED_TRADING_PAIRS_URL, urlBuilder.buildQueryString(),
                new TypeReference<GetSupportedTradingPairsResponse>() {});
        return response.getData();
    }

    /**
     * Get conversion quote (get-quote)
     */
    public ConvertQuote getQuote(GetQuoteRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        GetQuoteResponse response = httpClient.postWithBody(GET_QUOTE_URL, request,
                new TypeReference<GetQuoteResponse>() {});
        return response.getData();
    }

    /**
     * Accept quote and execute conversion (accept-quote)
     */
    public AcceptQuoteResponse acceptQuote(AcceptQuoteRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        return httpClient.postWithBody(ACCEPT_QUOTE_URL, request,
                new TypeReference<AcceptQuoteResponse>() {});
    }

    /**
     * Query conversion order history (query-order-history)
     */
    public ConvertOrderHistoryResponse queryOrderHistory(GetOrderHistoryRequest request) throws CoinsApiException {
        ValidationUtil.validate(request, Locale.ENGLISH);
        
        return httpClient.postWithBody(QUERY_ORDER_HISTORY_URL, request, 
                new TypeReference<ConvertOrderHistoryResponse>() {});
    }
}
