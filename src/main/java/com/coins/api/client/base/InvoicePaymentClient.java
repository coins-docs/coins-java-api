package com.coins.api.client.base;

import com.coins.api.client.CoinsApiConfig;
import com.coins.api.exception.CoinsApiException;
import com.coins.api.model.CreatePaymentRequestRequest;
import com.coins.api.model.GetPaymentRequestRequest;
import com.coins.api.model.PaymentRequestListResponse;
import com.coins.api.model.PaymentRequestOperationRequest;
import com.coins.api.model.PaymentRequestResponse;
import com.coins.api.util.ValidationUtil;
import com.coins.api.util.UrlBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Locale;

/**
 * Client for Payment Request API operations
 */
public class InvoicePaymentClient {
    
    // API endpoint constants
    private static final String PAYMENT_REQUEST_ENDPOINT = "openapi/v3/payment-request/payment-requests";
    private static final String GET_PAYMENT_REQUEST_ENDPOINT = "openapi/v3/payment-request/get-payment-request";
    private static final String CANCEL_PAYMENT_REQUEST_ENDPOINT = "openapi/v3/payment-request/delete-payment-request";
    private static final String PAYMENT_REQUEST_REMINDER_ENDPOINT = "openapi/v3/payment-request/payment-request-reminder";
    
    private final CoinsApiConfig config;
    private final HttpClient httpClient;
    
    public InvoicePaymentClient(CoinsApiConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);
    }
    
    /**
     * Create a new payment request
     * 
     * @param request Create payment request parameters
     * @return Created payment request response
     * @throws CoinsApiException if the API call fails
     */
    public PaymentRequestResponse createPaymentRequest(CreatePaymentRequestRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("payer_contact_info", request.getPayerContactInfo())
            .addParameter("receiving_account", request.getReceivingAccount())
            .addParameter("amount", request.getAmount() != null ? request.getAmount().toString() : null)
            .addParameter("message", request.getMessage())
            .addParameter("supported_payment_collectors", request.getSupportedPaymentCollectors())
            .addParameter("expires_at", request.getExpiresAt());
        
        return httpClient.post(PAYMENT_REQUEST_ENDPOINT, urlBuilder, new TypeReference<PaymentRequestResponse>() {});
    }
    
    /**
     * Get payment request details
     * 
     * @param request Get payment request parameters
     * @return Payment request list response
     * @throws CoinsApiException if the API call fails
     */
    public PaymentRequestListResponse getPaymentRequests(GetPaymentRequestRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("id", request.getId())
            .addParameter("start_time", request.getStartTime())
            .addParameter("end_time", request.getEndTime())
            .addParameter("limit", request.getLimit());
        
        return httpClient.get(GET_PAYMENT_REQUEST_ENDPOINT, urlBuilder, new TypeReference<PaymentRequestListResponse>() {});
    }
    
    /**
     * Cancel a payment request
     * 
     * @param request Payment request operation parameters
     * @return Cancellation result
     * @throws CoinsApiException if the API call fails
     */
    public PaymentRequestResponse cancelPaymentRequest(PaymentRequestOperationRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("id", request.getId());
        
        return httpClient.post(CANCEL_PAYMENT_REQUEST_ENDPOINT, urlBuilder, new TypeReference<PaymentRequestResponse>() {});
    }

    /**
     * Send payment request reminder
     * 
     * @param request Payment request operation parameters
     * @return Reminder result
     * @throws CoinsApiException if the API call fails
     */
    public Boolean sendPaymentRequestReminder(PaymentRequestOperationRequest request) throws CoinsApiException {
        ValidationUtil.validate(request);
        
        // Use optimized UrlBuilder for query string construction
        UrlBuilder urlBuilder = UrlBuilder.create("")
            .addParameter("id", request.getId());
        
        return httpClient.post(PAYMENT_REQUEST_REMINDER_ENDPOINT, urlBuilder, new TypeReference<Boolean>() {});
    }
}
